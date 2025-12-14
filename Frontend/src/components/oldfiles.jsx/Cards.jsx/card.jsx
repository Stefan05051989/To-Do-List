import { useState } from "react";

function Card({ tasks, status, onDelete, onMove }) {
  const [selectedTaskId, setSelectedTaskId] = useState("")

  // functie voor toggle optie
  function handleTaskClick(taskId){
    if(selectedTaskId === taskId){
      setSelectedTaskId("");
    }else{
      setSelectedTaskId(taskId)
    }
  }

  // functie voor 
  function handleAction(e, taskId){
    if ( action === "delete"){
      onDelete(taskId);
      // onder: else deed het niet.
    }else if (action){
      onMove(taskId, action)
    }
    setSelectedTaskId(null)
  }

  function dropDownOptions(){
    switch ( status){
      case "created":
        return(
          <>
          <option value="Move to : "></option>
          <option value="inprogress">In Progress</option>
          <option value="archived">Archive</option>
          <option value="delete">Delete</option>
          </>
        )
        case "inprogress":
        return(
          <>
          <option value="Move to : "></option>
          <option value="inprogress">In Progress</option>
          <option value="archived">Archive</option>
          <option value="detele">Delete</option>
          </>
        )
        case "done":
        return(
          <>
          <option value="Move to : "></option>
          <option value="inprogress">In Progress</option>
          <option value="archived">Archive</option>
          <option value="detele">Delete</option>
          </>
        )
        case "archived":
        return(
          <>
          <option value="Move to : "></option>
          <option value="inprogress">In Progress</option>
          <option value="archived">Archive</option>
          <option value="detele">Delete</option>
          </>
        )
        case "done":
        return(
          <>
          <option value="Move to : "></option>
          <option value="inprogress">In Progress</option>
          <option value="archived">Archive</option>
          <option value="detele">Delete</option>
          </>
        )

        default:
          return null
    }
  }
  return (
    <div className="card">
      {/* conditional rendering */}
      {tasks.length === 0 ? (
        <p className="cardEmpty">No Tasks</p>
        // als leeg, dan dit : <- (zo niet, dan dit.
      ) : (
        <ul className="cardList">
          {tasks.map((task) => (
            // onder : classname= etc.... binnen backticks (dubbel/single quotes registreren string.)
            <li key={task.id} className={`cardItem ${selectedTaskId === task.id ? "cardItemSelected" : ""}`} onClick={() => handTaskClick(task.id)}>
              <span className="cardText">{task.text}</span>
              {setSelectedTaskId === task.id && (
                  <select clasName="cardDropDown"
                  onClick={(e) => e.stopPropagation()}
                  onChange={(e) => handleAction(e, task)}
                  defaultValue=""
                  >
                    {dropDownOptions()}
                    </select>
              )}
            </li>
          ))}
        </ul>
      )}
    </div>
  )
}

export default Card