import React from "react";
import { Form, Button } from "react-bootstrap";

import WafepaAxios from "../../apis/WafepaAxios";

class EditCandidate extends React.Component {
  constructor(props) {
    super(props);

    let candidate = {
      fullName: "",
    skillsDto:[]
    };
    this.state = { candidate: candidate,
    candidateId:this.props.match.params.id,
    skillsId:[],
    skillsDto:[
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
  onToggle(index,e){
    let newItems = this.state.skillsDto.slice();
    newItems[index-1].checked = !newItems[index-1].checked

    this.setState({ skillsId: newItems })
  }
  setSkillsForCandidate(){
    let candidateSkills=this.state.candidate.skillsDto.slice();
    console.log(`Skills of this candidate is ${candidateSkills}`);
    let newItems=this.state.skillsDto.slice();
 console.log(``)
  candidateSkills.map((skill)=>{
    console.log(`this skill is ${skill.name}`);
 newItems[skill.id-1].checked=true;

  })
  this.setState({skillsId:newItems})
  
  console.log(this.state.skillsDto[2].checked)
  }
  componentWillMount() {
   
      this.getCandidate()
     
  }

  //#region API calls

  getCandidate() {
    WafepaAxios.get("/candidates/"+this.state.candidateId)
      .then((res) => {
        this.setState({ candidate: res.data });
        this.setSkillsForCandidate();
      })
      .catch((res) => {
        alert("Error occured please try again!");
      });
     
  }

 

  

  valueInputChanged(e) {
    let input = e.target;

    let name = input.name;
    let value = input.value;

    let candidate = this.state.candidate;
    candidate[name] = value;

    this.setState({candidate: candidate });
  }

   doEdit() {
    let candidate=this.state.candidate;
    let skills=[];
    this.state.skillsId.map((skill)=>{
      if(skill.checked){
        delete skill.checked
         skills.push(skill);
      }
      candidate.skillsDto=skills;
      this.setState({candidate:candidate});
      console.log(skill.id);
    })
    WafepaAxios.put("candidates/" + this.state.candidateId, this.state.candidate)
      .then(() => {
        this.props.history.push("/candidates");
      })
      .catch(() => alert("Something went wrong!"));
  }

  render() {
    if (this.state.candidateId && this.state.candidateId !== -1) {
      return (
        <div>
          <h1>Edit Candidate</h1>
          <Form>
            <Form.Group>
              <Form.Label>Name and Surname</Form.Label>
              <Form.Control
                name="fullName"
                as="input"
                value={this.state.candidate.fullName}
                onChange={(e) => {
                  this.valueInputChanged(e);
                }}
              />
            </Form.Group>
            <Form.Group>
              <Form.Label>Date of birth</Form.Label>
              <Form.Control
                name="dateOfBirth"
                as="input"
                value={this.state.candidate.dateOfBirth}
                onChange={(e) => this.valueInputChanged(e)}
              />
            </Form.Group>
            <Form.Group>
              <Form.Label>Contact Number</Form.Label>
              <Form.Control
                name="contactNumber"
                as="input"
                value={this.state.candidate.contactNumber}
                onChange={(e) => this.valueInputChanged(e)}
              />
            </Form.Group>
            <Form.Group>
              <Form.Label>Email</Form.Label>
              <Form.Control
                name="email"
                as="input"
                value={this.state.candidate.email}
                onChange={(e) => this.valueInputChanged(e)}
              />
            </Form.Group>
            <div>
        <ul>
        {this.state.skillsId.map((skill) =>
          <li key={skill.id}>
          <input type="checkbox" checked={skill.checked} onChange={this.onToggle.bind(this, skill.id)} />
          {`-----${skill.name}`}
          </li>
        )}
        </ul>
      <br/>
       
      </div>
            <Button onClick={() => this.doEdit()}>Edit</Button>
          </Form>
        </div>
      );
    } else {
      return <></>;
    }
  }
}

export default EditCandidate;
