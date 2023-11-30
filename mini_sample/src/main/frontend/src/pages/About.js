// eslint-disable-next-line
import React from "react";
import Nav from "../components/Nav";
import styled from "styled-components";
import Header from "../About/Header";
import Content from "../About/Content";
import Footer from "../About/Footer";

const Introduce = styled.div`
  height: 100vh;
  background-color: white;
  color: black;
  margin: 0;
  padding: 80px;
  h1 {
    padding: 0;
    margin: 0;
  }
`;

const About = () => {
  return (
    <div>
      <Introduce>
        <Header />
        <Content />
        <Footer />
      </Introduce>
      <Nav />
    </div>
  );
};
export default About;
