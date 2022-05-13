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
  return <form style={{width: "100px"}}>{props.children}</form>
}

function TextArea(props){
  return <textarea>{props.children}</textarea>
}

const formComponents = {
  width: 'inherit'
}

function App() {

  const [setTime,time] = useState("0:00");

  const fetchTime = async (value) => {
      try{
        const response = await fetch(`http://localhost:8080/v1/time/${value}`, {
          method: 'GET'
        });

        if (response.ok){

        }
      }
      catch(error){
        console.log(`error: ${error.getMessage()}`);
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
            <Button onclick={fetchTime()}>Fetch Time</Button>
        </Form>
        <TextArea></TextArea>
        
      </header>
    </div>
  );
}


export default App;
