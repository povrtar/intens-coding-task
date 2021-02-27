import React ,{Component}from "react";
import { Table, Button, Form, ButtonGroup } from "react-bootstrap";
import WafepaAxios from "../../apis/WafepaAxios";
class Candidates extends React.Component {
  constructor(props) {
    super(props);
    this.state = { 
      skillsId:[],
      candidates: [], 
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
        }
    ],
    skillIdes:'',
      selectedCandidateId: -1, 
      search:{fullName: ""},

    };
  }

  
  componentDidMount=()=>{
    this.getSkills();
    this.getCandidates();
  }

  onToggle(index, e){
    let newItems = this.state.skills.slice();
    newItems[index-1].checked = !newItems[index-1].checked

    this.setState({ skillsId: newItems })
  }
  getCandidates() {
    let config = {params:{}};

    if(this.state.search.fullName !== ""){
      config.params.fullName = this.state.search.fullName;
    }

    if(this.state.skillIdes!==''){
 
      config.params.skillIdes = this.state.skillIdes;
    }

    

    console.log(config.params.skillIdes);

    WafepaAxios.get("/candidates/",config)
      .then((res) => {
        this.setState({ candidates: res.data });
        console.log(this.state);
      })
      .catch((res) => {
        alert("Error occured please try again!");
      });
  }
getSkills=()=>{
  WafepaAxios.get("/skills/")
  .then((res) => {
    this.setState({ skills: res.data });
    console.log(this.state);
  })
  .catch((res) => {
    alert("Error occured please try again!");
  });
}
  
  goToAdd() {
    this.props.history.push("/candidates/add");
  }

  goToEdit(candidateId) {
    this.props.history.push("/candidates/edit/" + candidateId);
  }

  async doDelete(candidateId){
    try{
      await WafepaAxios.delete("/candidates/" + candidateId);
      this.getCandidates();
    }
    catch(error){
      alert("Couldn't delete the record");
    }
  }

  valueInputChange(event){
    let control = event.target;

    let name = control.name;
    let value = control.value;

    let search = this.state.search;
    search[name] = value;

    this.setState({search: search});
  }

  doSearch(){
    let string='';
    this.state.skillsId.map((skill)=>{
      if(skill.checked ){
        string=string.concat(","+skill.id);
  
       
        console.log("string "+string);
      }
     
    })
    this.setState({skillIdes:string});
    console.log(this.state.skillIdes)
    this.getCandidates();
  }

 
 

submit(){
    console.warn(this.state)
}

  

  render() {
    return (
      <div>
        <h1>Candidates</h1>

        <div style={{ marginBottom: "10px" }}>
          <Button
            onClick={() => {
              this.goToAdd();
            }}
          >
            Add
          </Button>
        </div>

        <Form>
            <Form.Group>
              <Form.Label>FullName</Form.Label>
              <Form.Control value={this.state.search.fullName} name="fullName" as="input" onChange={(e)=>this.valueInputChange(e)}></Form.Control>
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
            <Button onClick={()=>this.doSearch()}>Search</Button>
        </Form>
       
       

        <Table bordered striped>
          <thead>
            <tr>
              <th>Name and Surname</th>
              <th>Date of Birth</th>
              <th>Contact Number</th>
              <th>Email</th>
              <th colSpan={2}>Actions</th>
            </tr>
          </thead>
          <tbody>
            {this.state.candidates.map((candidate) => {
              return (
                <tr
                  onClick={() => {
                    this.setState({ selectedCandidateId: candidate.id });
                  }}
                  key={candidate.id}
                >
                  <td>{candidate.fullName}</td>
                  <td>{candidate.dateOfBirth}</td>
                  <td>{candidate.contactNumber}</td>
                  <td>{candidate.email}</td>
                
                  <td>
                    <Button
                      variant="warning"
                      onClick={() => this.goToEdit(candidate.id)}
                    >
                      Edit
                    </Button>

                    <Button variant="danger" onClick={() => this.doDelete(candidate.id)}>Delete</Button>
                  </td>
                </tr>
              );
            })}
          </tbody>
        </Table>
      </div>
    );
  }
}

export default Candidates;
