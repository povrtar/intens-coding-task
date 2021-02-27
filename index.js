import React from "react";
import ReactDOM from "react-dom";
import {
  Route,
  Link,
  HashRouter as Router,
  Switch,
  Redirect,
} from "react-router-dom";
import Home from "./components/Home";

import Candidates from "./components/candidates/Candidates";
import AddCandidate from "./components/candidates/AddCandidate";
import EditCandidate from "./components/candidates/EditCandidate";
import { Container, Navbar, Nav } from "react-bootstrap";

class App extends React.Component {
  
  render() {
    
      return (
        <div>
          <Router>
            <Navbar bg="dark" variant="dark" expand>
              <Navbar.Brand as={Link} to="/">
                Intrens-Task
              </Navbar.Brand>
              <Nav>
              
                <Nav.Link as={Link} to="/candidates">
                  Candidates
                </Nav.Link>
              </Nav>
            
            </Navbar>

            <Container>
              <Switch>
                <Route exact path="/" component={Home} />
             
                <Route exact path="/candidates" component={Candidates} />
                <Route exact path="/candidates/add" component={AddCandidate} />
                <Route exact path="/candidates/edit/:id" component={EditCandidate} />
                <Route exact path="/login">
                  <Redirect to="/"></Redirect>
                </Route>
                
              </Switch>
            </Container>
          </Router>
        </div>
      );
    } 
  }


ReactDOM.render(<App />, document.querySelector("#root"));
