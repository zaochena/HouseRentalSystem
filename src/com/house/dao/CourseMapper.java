package com.house.dao;

import com.house.entity.Course;
import com.house.entity.CourseSelected;


public interface CourseMapper {
    /**
     * 查询课程id，用户名，评分
     * @return
     */
    public CourseSelected[] selectInfo();
    public Course selectCourseName(Course course);
}
