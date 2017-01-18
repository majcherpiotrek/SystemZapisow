package sample;

import exceptions.WrongGroupException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import java.util.ArrayList;

/**
 * Created by piotrek on 07.12.16.
 */
public class Course {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        if (term != course.term) return false;
        if (ECTS != course.ECTS) return false;
        if (name != null ? !name.equals(course.name) : course.name != null) return false;
        if (courseCode != null ? !courseCode.equals(course.courseCode) : course.courseCode != null) return false;
        if (department != course.department) return false;
        if (departmentName != null ? !departmentName.equals(course.departmentName) : course.departmentName != null)
            return false;
        if (fieldOfStudy != course.fieldOfStudy) return false;
        if (fieldOfStudyName != null ? !fieldOfStudyName.equals(course.fieldOfStudyName) : course.fieldOfStudyName != null)
            return false;
        if (groupTypes != null ? !groupTypes.equals(course.groupTypes) : course.groupTypes != null) return false;
        if (specialization != course.specialization) return false;
        if (specializationName != null ? !specializationName.equals(course.specializationName) : course.specializationName != null)
            return false;
        if (obligatory != null ? !obligatory.equals(course.obligatory) : course.obligatory != null) return false;
        if (groups != null ? !groups.equals(course.groups) : course.groups != null) return false;
        if (hasGroupTypes != null ? !hasGroupTypes.equals(course.hasGroupTypes) : course.hasGroupTypes != null)
            return false;
        return complete != null ? complete.equals(course.complete) : course.complete == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (courseCode != null ? courseCode.hashCode() : 0);
        result = 31 * result + term;
        result = 31 * result + (department != null ? department.hashCode() : 0);
        result = 31 * result + (departmentName != null ? departmentName.hashCode() : 0);
        result = 31 * result + (fieldOfStudy != null ? fieldOfStudy.hashCode() : 0);
        result = 31 * result + (fieldOfStudyName != null ? fieldOfStudyName.hashCode() : 0);
        result = 31 * result + (groupTypes != null ? groupTypes.hashCode() : 0);
        result = 31 * result + (specialization != null ? specialization.hashCode() : 0);
        result = 31 * result + (specializationName != null ? specializationName.hashCode() : 0);
        result = 31 * result + ECTS;
        result = 31 * result + (obligatory != null ? obligatory.hashCode() : 0);
        result = 31 * result + (groups != null ? groups.hashCode() : 0);
        result = 31 * result + (hasGroupTypes != null ? hasGroupTypes.hashCode() : 0);
        result = 31 * result + (complete != null ? complete.hashCode() : 0);
        return result;
    }

    private String name;
    private String courseCode;
    private int term;
    private Department department;
    private String departmentName;
    private FieldsOfStudies fieldOfStudy;
    private String fieldOfStudyName;

    private ArrayList<GroupTypes> groupTypes;

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    private Specialization specialization;
    private String specializationName;
    private int ECTS;
    private Boolean obligatory;
    private ObservableList<Group> groups;
    private ArrayList<Boolean>hasGroupTypes;
    private Boolean complete;

    public Course(String name, String courseCode, int term, Department department, FieldsOfStudies fieldOfStudy, ArrayList<GroupTypes> groupTypes, Specialization specialization, int ECTS, Boolean obligatory){
        this.name = name;
        this.courseCode = courseCode;
        this.term = term;
        this. department = department;
        this.departmentName = this.department.getName();
        this.fieldOfStudy = fieldOfStudy;
        this.fieldOfStudyName = this.fieldOfStudy.getName();
        this.groupTypes = groupTypes;
        hasGroupTypes = new ArrayList<>();
        for(int i = 0; i < this.groupTypes.size(); i++){
            hasGroupTypes.add(false);
        }
        this.specialization = specialization;
        this.specializationName = this.specialization.getName();
        this.ECTS = ECTS;
        this.obligatory = obligatory;
        this.complete = false;
        groups = FXCollections.observableArrayList();
    }

    public Boolean getComplete() {
        return complete;
    }

    public Course(){

        Boolean b = null;

        this.name = null;
        this.courseCode = null;
        this.term = 0;
        this. department = null;
        this.departmentName = null;
        this.fieldOfStudy = null;
        this.fieldOfStudyName = null;
        this.groupTypes = null;
        this.specialization = null;
        this.specializationName = null;
        this.ECTS = 0;
        this.obligatory = null;
        groups = FXCollections.observableArrayList();
    }

    public ArrayList<Boolean> getHasGroupTypes() {
        return hasGroupTypes;
    }

    public void addGroup(Group group) throws WrongGroupException{
        if(group.getDepartment() == this.department)
            if(group.getTerm() == this.term)
                if(group.getFieldOfStudy() == this.fieldOfStudy)
                    if(group.getSpecialization()==this.specialization){
                        groups.add(group);
                        return;
                    }
        throw new WrongGroupException("Grupa nie należy do tego kurs");
    }
    public void updateComplete(){

        int i = 0;
        for (GroupTypes gt: this.groupTypes) {
            boolean found = false;
            for(Group group : this.groups)
                if(group.getType().equals(gt)) {
                    found = true;
                    break;
                }
            if(!found) // jeśli nie znaleziono tego typu grupy
                this.hasGroupTypes.set(i,false);
            else
                this.hasGroupTypes.set(i,true);
            i++;
        }

        this.complete = this.isCourseComplete();
    }
    public boolean isCourseComplete(){
        for (Boolean b: hasGroupTypes) {
            if(b.equals(false)) return false;
        }
        return true;
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public FieldsOfStudies getFieldOfStudy() {
        return fieldOfStudy;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public int getECTS() {
        return ECTS;
    }

    public void setECTS(int ECTS) {
        this.ECTS = ECTS;
    }

    public Boolean getObligatory() {
        return obligatory;
    }

    public void setObligatory(Boolean obligatory) {
        this.obligatory = obligatory;
    }

    public ObservableList<Group> getGroups() {
        return groups;
    }

    public void setGroups(ObservableList<Group> groups) {
        this.groups = groups;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public String getFieldOfStudyName() {
        return fieldOfStudyName;
    }

    public String getSpecializationName() {
        return specializationName;
    }

    public String getGroupTypes() {
        String ret = "";
        for(GroupTypes g : groupTypes)
            ret += g.getName() + ",";
        return ret;
    }

    public ArrayList<GroupTypes> getGroupTypesList(){
        return groupTypes;
    }

    public void setGroupTypes(ArrayList<GroupTypes> groupTypes) {
        this.groupTypes = groupTypes;
    }

    public void setFieldOfStudy(FieldsOfStudies fieldOfStudy) {
        this.fieldOfStudy = fieldOfStudy;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public void setFieldOfStudyName(String fieldOfStudyName) {
        this.fieldOfStudyName = fieldOfStudyName;
    }

    public void setSpecializationName(String specializationName) {
        this.specializationName = specializationName;
    }

    public String generateNewGroupCode(GroupTypes type){
        String generatedGroupCode ="";
        generatedGroupCode+=this.getCourseCode();

        int groupNumber=1;
        for(Group g : this.getGroups())
            if(g.getType().equals(type))
                groupNumber++;

        generatedGroupCode+=Integer.toString(groupNumber);
        generatedGroupCode+=type.toString();
        generatedGroupCode+= this.courseCode;
        return  generatedGroupCode;
    }
}
