package com.example.day20_exercise.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TaskTracker {

    int ID;
    String title;
    String description;
    String status;

}
