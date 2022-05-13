import logo from './logo.svg';
import './App.css';
import clock from './clock.png';
import React, { useState } from 'react';
// date-fns
import { AdapterDateFns } from '@mui/x-date-pickers/AdapterDateFns';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { TimePicker } from '@mui/x-date-pickers';
import { Button, TextField } from '@mui/material';

function Title(props) {
  return <h1>{props.children}</h1>
}

function Form(props){
  return <form style={{width: "300px"}}>{props.children}</form>
}

function TextArea(props){
  return <textarea id={props.id}>{props.children}</textarea>
}

const formComponents = {
  width: 'inherit'
}

function App() {

  const [time, setTime] = useState("0:00");

  const fetchTime = async () => {

    let payload = new Date(time);
    payload = `${payload.getHours()}:${payload.getMinutes()}`;
    console.log(`new payload: ${payload}`);

      try{
        const response = await fetch(`/v1/time/${payload}`, {
          mode: 'no-cors',
          cache: 'no-cache',
          headers: {
            'Content-type': 'text/plain'
          }
        });

        console.log(`Here is the response: ${response}`);

        if (response.ok){
          const json = await response.json();
          document.getElementById('response').value = json.body;
        }
        else
          throw new Error('Response not ok!');

        
      }
      catch(error){
        console.log(`error: ${error.message}`);
      }
  }

  return (
    <div className="App">
      <header className="App-header">
        <Title>Welcome to Time Clock</Title>
        <img src={clock} className="App-logo" alt="logo" />
        <Form>
            <LocalizationProvider dateAdapter={AdapterDateFns}>
              <TimePicker label="Basic example"
                value={time}
                style={{borderColor: "white"}}
                onChange={(newValue) => {
                  setTime(newValue);
                }}
                renderInput={(params) => <TextField {...params}  />}
              />
            </LocalizationProvider>
            <Button onClick={fetchTime}>Fetch Time</Button>
        </Form>
        <TextArea id="response"></TextArea>
        
      </header>
    </div>
  );
}


export default App;
