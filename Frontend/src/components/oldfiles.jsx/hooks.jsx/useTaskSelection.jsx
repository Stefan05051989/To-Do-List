import { useState } from "react";

export function useTaskSelection(){
    const [selectedTaskId, setSelectedTaskId] = useState(null);

    function selectTask(taskId){
        if(selectedTaskId === taskId){
            setSelectedTaskId(null);
        } else { 
            setSelectedTaskId(taskId)
        }
    }

    function clearSelection(){
        setSelectedTaskId(null)
    }
    function isSelected(taskId){
        return selectedTaskId === taskId
    }

    return {
        selectedTaskId,
        selectTask,
        clearSelection,
        isSelected,
    }

}