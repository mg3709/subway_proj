package com.kh.mini_sample.service;

import com.kh.mini_sample.vo.NodeTransVO;
import com.kh.mini_sample.vo.PathVO;
import com.kh.mini_sample.vo.StationVO;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

import static com.kh.mini_sample.vo.StationsVO.STATION_SIZE;
@Service
public class MinimumTransferService {
    static final Long INF = Long.MAX_VALUE;

    //노드의 크기, 출발지
    public PathVO Dijkstra(String start, String dest, StationVO[] stations, ArrayList<NodeTransVO>[] orinGraph,List<Boolean>whatDayList) throws ParseException {
        Map<String,ArrayList<Integer>> stationMap = findStation(start,dest,stations);
        List<Date[]> arvTmList = new ArrayList<>();
        List<Date[]> dptTmList = new ArrayList<>();
        List<long[]> distList = new ArrayList<>();
        List<int[]> prevList = new ArrayList<>();
        List<Integer> shortest = new ArrayList<>();
        List<Integer> startList = new ArrayList<>();
        List<Integer> destList = new ArrayList<>();

        for(int s = 0; s<stationMap.get(start).size(); s++ ){
            int startIndex = stationMap.get(start).get(s);

            for(int d = 0; d<stationMap.get(dest).size(); d++) {
                ArrayList<NodeTransVO>[] graph = deepCopy(orinGraph);
                PriorityQueue<NodeTransVO> pq = new PriorityQueue<>();
                pq.offer(new NodeTransVO(startIndex, 0,0));

                boolean[] check = new boolean[STATION_SIZE];
                long[] dist = new long[STATION_SIZE];
                int[] prev = new int[STATION_SIZE];
                Date[] arvTm = new Date[STATION_SIZE];
                Date[] dptTm = new Date[STATION_SIZE];
                String[] trainNumber = new String[STATION_SIZE];
                int []totalTrans = new int[STATION_SIZE];

                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                Date dptTable = null; //열차도착시간표
                Date arvTable = null; //열차출발시간표
                long waitTime = 0; //대기한 시간;
                long durationTime = 0; // 소요 시간;
                Arrays.fill(totalTrans,Integer.MAX_VALUE);
                Arrays.fill(trainNumber,"");
                Arrays.fill(dist, INF);
                Arrays.fill(prev, -1); // -1은 경로 상 이전 정점이 없다는 것을 나타냅니다.
                totalTrans[startIndex] = 0;
                dist[startIndex] = 0;
                arvTm[startIndex] = new Date();
                arvTm[startIndex].setTime((arvTm[startIndex].getTime()-(arvTm[startIndex].getTime()%1000)));
                Arrays.fill(dptTm,new Date(INF));
                dptTm[startIndex] = new Date();
                dptTm[startIndex].setTime(dptTm[startIndex].getTime());
                int destIndex = stationMap.get(dest).get(d);

                while (!pq.isEmpty()) {
                    int nowVertex = pq.poll().getIndex();

                    if (check[nowVertex]) continue;
                    check[nowVertex] = true;

                    for (NodeTransVO next : graph[nowVertex]) {
                        int cnt = 0;
                        LocalDateTime localDateTime = LocalDateTime.now();
                        int currentHour = localDateTime.getHour(); //현재시간
                        boolean whatDay;

                        String today = localDateTime.getYear() + String.format("%02d", localDateTime.getMonthValue()) + String.format("%02d", localDateTime.getDayOfMonth());
                        if (stations[nowVertex].getTransfer().contains(String.valueOf(next.getIndex())) || stations[next.getIndex()].getTransfer().contains(String.valueOf(nowVertex))) {
                            if(dptTm[next.getIndex()].getTime() > arvTm[nowVertex].getTime()) dptTm[next.getIndex()] = arvTm[nowVertex];
                            next.setCount(1);
                            next.setTrainN("trans");
                        } else {
                            for (int i = currentHour; i < currentHour + 24; i++) {
                                whatDay = whatDayList.get(0);
                                if(i == 0) whatDay = whatDayList.get(2);
                                if (i == 24) {
                                    today = String.valueOf((Integer.parseInt(today)+1));
                                }
                                if (i >= 25) whatDay = whatDayList.get(1);

                                boolean isHoliday = whatDay;
                                List<String> whatDayDpt = isHoliday
                                        ? stations[nowVertex].getHoliDptTime()[i % 24] : stations[nowVertex].getWeekDptTime()[i % 24];
                                List<String> whatDayDptTrn = isHoliday
                                        ? stations[nowVertex].getHoliDptTrnNo()[i % 24] : stations[nowVertex].getWeekDptTrnNo()[i % 24];

                                for (int j = 0; j < whatDayDpt.size(); j++) {
                                    int trainN = -1;
                                    int trans = 0;
                                    dptTable = sdf.parse(today + whatDayDpt.get(j)); //열차가 출발하는 시간
                                    waitTime = (dptTable.getTime() - arvTm[nowVertex].getTime()); //열차가 오는데 걸리는시간
                                    if(nowVertex != startIndex && !trainNumber[nowVertex].equals(whatDayDptTrn.get(j))){
                                        if((!trainNumber[nowVertex].equals("trans")) &&stations[nowVertex].getLn_cd().equals(stations[next.getIndex()].getLn_cd())){
                                            waitTime = (dptTable.getTime() - (arvTm[nowVertex].getTime()+60000)); //열차가 오는데 걸리는시간
                                            if(waitTime >= 0) {
                                                waitTime+=60000;
                                            }
                                        }
                                    }

                                    if (waitTime < 0) continue;
                                    //열차가 떠난건 11시인데 다음역에 도착하는게 12시면 열차가 있다는사실을 인지를못해버림
                                    for (int k = 0; k < 2; k++) {
                                        List<String> whatDayArv = isHoliday
                                                ? stations[next.getIndex()].getHoliArvTime()[(i + k) % 24] : stations[next.getIndex()].getWeekArvTime()[(i + k) % 24];
                                        List<String> whatDayArvTrn = isHoliday
                                                ? stations[next.getIndex()].getHoliArvTrnNo()[(i + k) % 24] : stations[next.getIndex()].getWeekArvTrnNo()[(i + k) % 24];
                                        trainN = whatDayArvTrn.indexOf(whatDayDptTrn.get(j)); //열차 방향확인 //다음시간도 고려해줘야함
                                        if (trainN != -1) {
                                            arvTable = sdf.parse(today + whatDayArv.get(trainN));
                                            if((i%24) + k == 24) arvTable = sdf.parse((Integer.parseInt(today)+1)+whatDayArv.get(trainN));//하드코딩
                                            break;
                                        }
                                    }

                                    if (trainN == -1) continue;

                                    durationTime = (arvTable.getTime() - dptTable.getTime()); //이동 소요시간

                                    if (durationTime >= 0) {
                                        cnt++;
                                        if(waitTime + durationTime <= next.getCost()) {
                                            if(dptTm[nowVertex].getTime() < dptTable.getTime()) {
                                                if (dptTm[next.getIndex()].getTime() > dptTable.getTime()) {
                                                    dptTm[next.getIndex()] = dptTable;
                                                }
                                            }
                                            next.setCount(trans);
                                            next.setTrainN(whatDayDptTrn.get(j));
                                            next.setCost(waitTime + durationTime); //열차가오는데 걸리는시간+열차가 다음역에 도착하는시간 //총 소요시간
                                        }
                                        if(cnt == 2)break;
                                    }
                                }
                                if (cnt == 2) break; //내가 가려는 방향의 열차를 두번 확인했으면나감
                            }

                        }
                        if(next.getCost() != INF) {
                            if (totalTrans[nowVertex] + next.getCount() < totalTrans[next.getIndex()]) {
                                totalTrans[next.getIndex()] = totalTrans[nowVertex] + next.getCount();
                                trainNumber[next.getIndex()] = next.getTrainN();
                                dist[next.getIndex()] = dist[nowVertex] + next.getCost();
                                prev[next.getIndex()] = nowVertex; // 이전 정점 정보 갱신
                                arvTm[next.getIndex()] = new Date((arvTm[nowVertex].getTime() + next.getCost()));
                                pq.offer(new NodeTransVO(next.getIndex(), dist[next.getIndex()], totalTrans[next.getIndex()]));
                            }
                            else if (dist[next.getIndex()] == dist[nowVertex] + next.getCost()) {
                                if(dptTm[next.getIndex()].getTime() < dptTable.getTime()){
                                    dptTm[next.getIndex()].setTime(dptTm[nowVertex].getTime()+next.getCost());
                                    totalTrans[next.getIndex()] = totalTrans[nowVertex] + next.getCount();
                                    trainNumber[next.getIndex()] = next.getTrainN();
                                    dist[next.getIndex()] = dist[nowVertex] + next.getCost();
                                    prev[next.getIndex()] = nowVertex; // 이전 정점 정보 갱신
                                    arvTm[next.getIndex()] = new Date((arvTm[nowVertex].getTime() + next.getCost()));
                                    pq.offer(new NodeTransVO(next.getIndex(), dist[next.getIndex()], totalTrans[next.getIndex()]));
                                }
                            }
                        }
                    }
                }
                startList.add(startIndex);
                destList.add(destIndex);
                shortest.add(totalTrans[destIndex]);
                arvTmList.add(arvTm);
                dptTmList.add(dptTm);
                distList.add(dist);
                prevList.add(prev);
                if(shortest.size() > 1){
                    if(shortest.get(0) < shortest.get(1)){
                        removeList(arvTmList,dptTmList,distList,prevList,shortest,startList,destList,1);
                    } else if (shortest.get(0) == shortest.get(1)) {
                        if(arvTmList.get(0)[destIndex].getTime() < arvTmList.get(1)[destIndex].getTime()){
                            removeList(arvTmList,dptTmList,distList,prevList,shortest,startList,destList,1);
                        } else removeList(arvTmList,dptTmList,distList,prevList,shortest,startList,destList,0);
                    } else removeList(arvTmList,dptTmList,distList,prevList,shortest,startList,destList,0);
                }
            }
        }



        if(distList.get(0)[destList.get(0)] == INF) {
            System.out.println(stations[startList.get(0)].getName() + "에서 " + stations[destList.get(0)].getName() + "까지의 경로: 경로가 존재하지 않습니다.");
            return null;
        } else {
            System.out.print(stations[startList.get(0)].getName() + "에서 " + stations[destList.get(0)].getName() + "까지의 경로: ");
            List<String> path = new ArrayList<>();
            addPath(prevList.get(0), destList.get(0),stations,arvTmList.get(0),path,dptTmList.get(0),destList.get(0));
            return new PathVO("최소환승",(distList.get(0)[destList.get(0)]/60/1000),path,arvTmList.get(0)[startList.get(0)],arvTmList.get(0)[destList.get(0)]); //소요시간,경로,출발시간,도착시간
        }

    }
    public int countTransfers(int[] prev, int i,StationVO[] stations) {  //i목적지 인덱스
        int transfers = 0;
        while (prev[i] != -1) {
            // 이전 노드(prev[i])와 현재 노드(i)의 역 코드가 다르면 환승으로 간주
            if (!stations[prev[i]].getLn_cd().equals(stations[i].getLn_cd())) {
                transfers++;
            }
            i = prev[i]; // 이전 노드로 이동
        }
        return transfers;
    }

    // 경로 출력 함수
    public void addPath(int[] prev, int i, StationVO[] stations, Date []arvTm, List<String> path , Date [] dptTm, Integer dest) {
        if(prev[i] != -1) {
            addPath(prev, prev[i],stations,arvTm,path,dptTm,dest);
        }
        if(prev[i] == -1){
            path.add("출발시간 : " + arvTm[i].toString());
            path.add(stations[i].getIndex()+ " : " +stations[i].getName()+" : ");
        } else if (i == dest) {
            path.set(path.size()-1,path.get(path.size()-1)+dptTm[i].toString());
            path.add(stations[i].getIndex()+ " : " +stations[i].getName()+" : "+arvTm[i].toString());
        }
        else  {
            path.set(path.size()-1,path.get(path.size()-1)+dptTm[i].toString());
            path.add(stations[i].getIndex()+ " : " +stations[i].getName()+" : ");
        }
    }


    public Map<String,ArrayList<Integer>> findStation(String start,String dest,StationVO[] stations){
        ArrayList<Integer> stationS = new ArrayList<>();
        ArrayList<Integer> stationD = new ArrayList<>();
        for(int i = 0; i< stations.length; i++){
            if(stations[i].getName().equals(start)) stationS.add(i);
            if(stations[i].getName().equals(dest)) stationD.add(i);
        }
        Map<String,ArrayList<Integer>> stationMap = new HashMap<>();
        stationMap.put(start,stationS);
        stationMap.put(dest,stationD);
        return stationMap;
    }
    public void removeList(List<Date[]> arvTmList, List<Date[]> dptTmList, List<long[]> distList,List<int[]> prevList,List<Integer> shortest,
                           List<Integer> startList , List<Integer> destList,int i){
        arvTmList.remove(i);
        dptTmList.remove(i);
        distList.remove(i);
        prevList.remove(i);
        shortest.remove(i);
        startList.remove(i);
        destList.remove(i);
    }
    public ArrayList<NodeTransVO>[] deepCopy(ArrayList<NodeTransVO>[] graph) {
        ArrayList<NodeTransVO>[] newGraph = new ArrayList[graph.length];
        for (int i = 0; i < graph.length; i++) {
            newGraph[i] = new ArrayList<NodeTransVO>();
            for (int j = 0; j < graph[i].size(); j++) {
                NodeTransVO node = new NodeTransVO(graph[i].get(j).getIndex(),graph[i].get(j).getCost(),0);
                newGraph[i].add(node);
            }
        }
        return newGraph;
    }

}

