import React from "react";
import styled from "styled-components";
import backImg from "../img/backImg.jpg";
import { useNavigate } from "react-router-dom";
import { motion } from "framer-motion";

const Container = styled.div`
  margin: 2rem auto;
  .backImg {
    background-image: url(${backImg});
    background-repeat: no-repeat;
    background-size: cover;
    background-position: center;
    width: 100%;
    height: 30rem;
  }
  .cover {
    background-color: rgba(0, 0, 0, 0.5);
    width: 100%;
    height: 30rem;
    display: flex;
    justify-content: space-around;
    align-items: center;
    flex-direction: column;
  }
  h1 {
    color: white;
  }
  button {
    background-color: purple;
    color: white;
    width: 10rem;
    height: 2rem;
    font-weight: bold;
  }
`;

const Header = () => {
  const nav = useNavigate();

  return (
    <Container>
      <motion.div
        initial={{ opacity: 0, y: -30 }}
        animate={{ opacity: 1, y: 0 }}
        className="backImg"
      >
        <div className="cover">
          <h1>INTRODUCE</h1>
          <motion.button
            whileHover={{
              scale: 1.1,
            }}
            onClick={() => nav("/SubwayMap")}
          >
            GET START
          </motion.button>
        </div>
      </motion.div>
    </Container>
  );
};

export default Header;
