/*
Stefan Kiers
06-12-'25
function for creating a task with props id (current date) text, and status passed from taskstatus. 
*/
//import { useState } from "react"
import Card from "./card"

function Column({ title, status, tasks, onDelete, onMove }) {

  return (
    <div className="column">
      <h3 className="columnTitle">{title}</h3>
        <Card
        tasks={tasks}
        status={status}
        onDelete={onDelete}
        onMove={onMove}
        />
    </div>
  )
}

export default Column