package com.example.day20_exercise.TaskTrackerSystem;


import com.example.day20_exercise.ApiResponse.ApiResponse;
import com.example.day20_exercise.Model.TaskTracker;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/task-tracker-system")
public class TaskTrackerController {

    ArrayList<TaskTracker> taskTrackers = new ArrayList<>();

    @GetMapping("/get")
    public ArrayList<TaskTracker> getTasks() {
        return this.taskTrackers;
    }


    @PostMapping("/add")
    public ApiResponse addTask (@RequestBody TaskTracker taskTracker){

        for (TaskTracker task : taskTrackers) {
            if(task.getID() == taskTracker.getID()){
                return new ApiResponse("task already exists");
            }
        }

        this.taskTrackers.add(taskTracker);
        return new ApiResponse("Task added");
    }

    @PutMapping("/update/{index}")
    public ApiResponse updateTask(@PathVariable int index ,@RequestBody TaskTracker taskTracker){
        taskTrackers.set(index,taskTracker);
        return new ApiResponse( "Task updated");
    }


    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteTask(@PathVariable int index){

        this.taskTrackers.remove(index);

        return new ApiResponse("Task deleted");
    }


    @PutMapping("/status-change/{index}")
    public ApiResponse statusChange(@PathVariable int index){

        if (index > taskTrackers.size() || index < 0) {
            return new ApiResponse("Task not exist");
        }

          TaskTracker tem = this.taskTrackers.get(index);

          if (tem.getStatus().equalsIgnoreCase("done")) {
              tem.setStatus("not done");
          }else if (tem.getStatus().equalsIgnoreCase("not done")) {
              tem.setStatus("done");
          }

          return new ApiResponse("status changed");
    }


    @GetMapping("/search-by-title/{title}")
    public TaskTracker searchByTitle(@PathVariable String title){
        for (TaskTracker task : taskTrackers) {
            if (task.getTitle().equals(title)) {

                return task;
            }
        }
        return null;
    }
}
