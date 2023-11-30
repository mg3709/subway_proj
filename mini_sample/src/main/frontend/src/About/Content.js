import styled from "styled-components";
import Img1 from "../img/help2.png";
import Img2 from "../img/help3.png";
import Img3 from "../img/help4.png";
import Img4 from "../img/help5.png";
import Img5 from "../img/help7.png";
import { motion } from "framer-motion";
import { useState } from "react";

const Container = styled.div`
  ul {
    padding: 0;
    display: flex;
    justify-content: center;
    margin: 5rem auto;
  }
  li {
    box-shadow: 0px 2px 8px rgba(0, 0, 0, 0.5);
    display: flex;
    justify-content: center;
    align-items: center;
    border: 1px solid black;
    list-style: none;
    height: 8rem;
    width: 20rem;
    background-color: purple;
    color: white;
    font-weight: bold;
  }
  li:hover {
    background-color: #bf00ff;
  }
  .box {
    height: 30rem;
    margin: auto 8rem;
    box-shadow: 0px 2px 8px rgba(0, 0, 0, 0.5);
    display: flex;
    justify-content: space-around;
    align-items: center;
    background-color: #ccc;
    border-radius: 12px;
  }
  .img1 {
    background-image: url(${Img1});
    background-position: center;
    background-repeat: no-repeat;
    background-size: cover;
    width: 25rem;
    height: 25rem;
  }
  .img2 {
    background-image: url(${Img2});
    background-repeat: no-repeat;
    background-size: cover;
    width: 25rem;
    height: 25rem;
  }
  .img3 {
    background-image: url(${Img3});
    background-repeat: no-repeat;
    background-size: cover;
    width: 25rem;
    height: 25rem;
  }
  .img4 {
    background-image: url(${Img4});
    background-repeat: no-repeat;
    background-size: cover;
    width: 25rem;
    height: 25rem;
  }
  .img5 {
    background-image: url(${Img5});
    background-repeat: no-repeat;
    background-size: cover;
    width: 25rem;
    height: 25rem;
  }
  .cover {
    background-color: rgba(0, 0, 0, 0.5);
    width: 25rem;
    height: 25rem;
    color: white;
    display: flex;
    justify-content: space-around;
    align-items: center;
    flex-direction: column;
  }
`;

const Content = () => {
  const [select, setSelect] = useState(1);

  return (
    <Container>
      <motion.div
        initial={{ opacity: 0, y: -30 }}
        animate={{ opacity: 1, y: 0 }}
      >
        <ul>
          <li onClick={() => setSelect(1)}>
            <p>역 고르기</p>
          </li>
          <li onClick={() => setSelect(2)}>
            <p>역 정보</p>
          </li>
          <li onClick={() => setSelect(3)}>
            <p>마커 사용</p>
          </li>
          <li onClick={() => setSelect(4)}>
            <p>경로 탐색</p>
          </li>
          <li onClick={() => setSelect(5)}>
            <p>역 검색</p>
          </li>
        </ul>
      </motion.div>

      {select === 1 && (
        <motion.div
          initial={{ opacity: 0, y: -30 }}
          animate={{ opacity: 1, y: 0 }}
          className="box"
        >
          <div className="img1">
            <motion.div
              style={{ opacity: 0 }}
              whileHover={{ opacity: 1 }}
              className="cover"
            >
              <h2>TITLE</h2>
              <p>CONTENT</p>
            </motion.div>
          </div>
          <p>DESCRIPTION</p>
        </motion.div>
      )}

      {select === 2 && (
        <motion.div
          initial={{ opacity: 0, y: -30 }}
          animate={{ opacity: 1, y: 0 }}
          className="box"
        >
          <div className="img2">
            <motion.div
              style={{ opacity: 0 }}
              whileHover={{ opacity: 1 }}
              className="cover"
            >
              <h2>TITLE</h2>
              <p>CONTENT</p>
            </motion.div>
          </div>
          <p>DESCRIPTION</p>
        </motion.div>
      )}

      {select === 3 && (
        <motion.div
          initial={{ opacity: 0, y: -30 }}
          animate={{ opacity: 1, y: 0 }}
          className="box"
        >
          <div className="img3">
            <motion.div
              style={{ opacity: 0 }}
              whileHover={{ opacity: 1 }}
              className="cover"
            >
              <h2>TITLE</h2>
              <p>CONTENT</p>
            </motion.div>
          </div>
          <p>DESCRIPTION</p>
        </motion.div>
      )}

      {select === 4 && (
        <motion.div
          initial={{ opacity: 0, y: -30 }}
          animate={{ opacity: 1, y: 0 }}
          className="box"
        >
          <div className="img4">
            <motion.div
              style={{ opacity: 0 }}
              whileHover={{ opacity: 1 }}
              className="cover"
            >
              <h2>TITLE</h2>
              <p>CONTENT</p>
            </motion.div>
          </div>
          <p>DESCRIPTION</p>
        </motion.div>
      )}

      {select === 5 && (
        <motion.div
          initial={{ opacity: 0, y: -30 }}
          animate={{ opacity: 1, y: 0 }}
          className="box"
        >
          <div className="img5">
            <motion.div
              style={{ opacity: 0 }}
              whileHover={{ opacity: 1 }}
              className="cover"
            >
              <h2>TITLE</h2>
              <p>CONTENT</p>
            </motion.div>
          </div>
          <p>DESCRIPTION</p>
        </motion.div>
      )}
    </Container>
  );
};

export default Content;
