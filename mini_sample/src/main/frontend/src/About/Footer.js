import React from "react";
import Img1 from "../img/help6.png";
import Img2 from "../img/help1.png";
import styled from "styled-components";
import { motion, useScroll, useTransform } from "framer-motion";

const Container = styled.div`
  margin-top: 5rem;
  .content {
    display: flex;
    justify-content: center;
    align-items: center;
    margin: 5rem auto;
    gap: 5rem;
  }
  h1 {
    text-align: center;
  }
  img {
    width: 30rem;
    height: 30rem;
  }
`;

const Footer = () => {
  const { scrollY } = useScroll();
  const opacityY = useTransform(scrollY, [300, 1000], [0, 1]);
  const titleY = useTransform(scrollY, [300, 1000], [500, 0]);

  return (
    <Container>
      <motion.h1 style={{ opacity: opacityY, y: titleY }}>ETC...</motion.h1>
      <motion.div style={{ opacity: opacityY, y: titleY }} className="content">
        <div>
          <img src={Img1} alt="img" />
          <p>채팅방도 있어요!!!</p>
        </div>
        <div>
          <img src={Img2} alt="img" />
          <p>자전거 위치 검색</p>
        </div>
      </motion.div>
    </Container>
  );
};

export default Footer;
