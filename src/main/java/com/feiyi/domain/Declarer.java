package com.feiyi.domain;

import lombok.Data;

import java.util.List;

@Data
public class Declarer {
    private int id;
    private String person_code;
    private String name;
    private int category_id;
    private String sex;
    private int nation_id;
    private String project_code;
    private String report_area;
    //项目类型
    private List<Category> categoryList;
    //民族
    private List<Nation > nationsList;
    //项目编号
    private List<Projects> projectsList;

    public Declarer(int id, String person_code, String name, int category_id, String sex, int nation_id, String project_code, String report_area, List<Category> categoryList, List<Nation> nationsList, List<Projects> projectsList) {
        this.id = id;
        this.person_code = person_code;
        this.name = name;
        this.category_id = category_id;
        this.sex = sex;
        this.nation_id = nation_id;
        this.project_code = project_code;
        this.report_area = report_area;
        this.categoryList = categoryList;
        this.nationsList = nationsList;
        this.projectsList = projectsList;
    }

    public Declarer() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPerson_code() {
        return person_code;
    }

    public void setPerson_code(String person_code) {
        this.person_code = person_code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getNation_id() {
        return nation_id;
    }

    public void setNation_id(int nation_id) {
        this.nation_id = nation_id;
    }

    public String getProject_code() {
        return project_code;
    }

    public void setProject_code(String project_code) {
        this.project_code = project_code;
    }

    public String getReport_area() {
        return report_area;
    }

    public void setReport_area(String report_area) {
        this.report_area = report_area;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public List<Nation> getNationsList() {
        return nationsList;
    }

    public void setNationsList(List<Nation> nationsList) {
        this.nationsList = nationsList;
    }

    public List<Projects> getProjectsList() {
        return projectsList;
    }

    public void setProjectsList(List<Projects> projectsList) {
        this.projectsList = projectsList;
    }

    @Override
    public String toString() {
        return "Declarer{" +
                "id=" + id +
                ", person_code='" + person_code + '\'' +
                ", name='" + name + '\'' +
                ", category_id=" + category_id +
                ", sex='" + sex + '\'' +
                ", nation_id=" + nation_id +
                ", project_code='" + project_code + '\'' +
                ", report_area='" + report_area + '\'' +
                ", categoryList=" + categoryList +
                ", nationsList=" + nationsList +
                ", projectsList=" + projectsList +
                '}';
    }
}
