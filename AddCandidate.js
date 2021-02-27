import React from "react";
import { Form, Button } from "react-bootstrap";
import WafepaAxios from "../../apis/WafepaAxios";
import Candidates from "./Candidates"

class AddCandidate extends React.Component {
  constructor(props) {
    super(props);

    let candidate = {
      fullName: "",
      dateOfBirth:"",
      contactNumber:0,
      email:"",
      skillsDto: []
    
    };
    this.state = {
       candidate: candidate,
       skills:[
        {
            "id": 1,
            "name": "Java Programing"
        },
        {
            "id": 2,
            "name": "C# Programing"
        },
        {
            "id": 3,
            "name": "Database design"
        },
        {
            "id": 4,
            "name": "English language"
        },
        {
            "id": 5,
            "name": "German lenguage"
        }]
  

}

  }
  onToggle(index, e){
    let newItems = this.state.skills.slice();
    newItems[index-1].checked = !newItems[index-1].checked

    this.setState({ skillsId: newItems })
  }
  valueInputChange(event){
    let control = event.target;
    let name = control.name;
    let value = control.value;

    let candidate = this.state.candidate;
    candidate[name] = value;

    this.setState({candidate: candidate});
  }

  async doAdd(){
    this.state.skillsId.map((skill)=>{
      if(skill.checked){
        let candidate=this.state.candidate;
        let skills=candidate.skillsDto;
        delete skill.checked
         skills.push(skill);
         candidate.skillsDto=skills;
        this.setState({candidate:candidate});
        console.log(skill.id);
      }
    })
    console.log(this.state.candidate);
    try{
      await WafepaAxios.post("/candidates/", this.state.candidate);
      this.props.history.push("/candidates");
    }
    catch(error){
      alert("Couldn't save the candidate.")
    }
  }

  render() {
    return (
      <div>
        <h1>Add Candidate</h1>

        <Form>
          <Form.Group>
            <Form.Label>Name and Surname</Form.Label>
            <Form.Control onChange={(event) => this.valueInputChange(event)} name="fullName" value={this.state.candidate.fullName} as="input"></Form.Control>
          </Form.Group>
          <Form.Group>
            <Form.Label>Date of Birth</Form.Label>
            <Form.Control onChange={(event) => this.valueInputChange(event)} name="dateOfBirth" value={this.state.dateOfBirth} as="input"></Form.Control>
          </Form.Group>
          <Form.Group>
            <Form.Label>Contact Nuber</Form.Label>
            <Form.Control onChange={(event) => this.valueInputChange(event)} name="contactNumber" value={this.state.candidate.contactNumber} as="input"></Form.Control>
          </Form.Group>
          <Form.Group>
          <Form.Label>Email</Form.Label>
          <Form.Control onChange={(event) => this.valueInputChange(event)} name="email" value={this.state.candidate.email} as="input"></Form.Control>
        </Form.Group>
        <div>
        <ul>
        {this.state.skills.map((skill) =>
          <li key={skill.id}>
          <input type="checkbox" onChange={this.onToggle.bind(this, skill.id)} />
          {`-----${skill.name}`}
          </li>
        )}
        </ul>
      <br/>
       
      </div>
          <Button variant="primary" onClick={()=>this.doAdd()}>Add</Button>
         
        </Form>

      </div>
    );
  }
}

export default AddCandidate;
