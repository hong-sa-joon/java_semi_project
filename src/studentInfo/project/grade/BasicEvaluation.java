package studentInfo.project.grade;

public class BasicEvaluation implements GradeEvaluation {
    // 일반과목 학점 산출
    @Override
    public String getGrade(int point) {
        String grade; // 등급 저장

        if (point >= 90) {
            grade = "A";
        } else if (point >= 80) {
            grade = "B";
        } else if (point >= 70) {
            grade = "C";
        } else if (point >= 55) {
            grade = "D";
        } else {
            grade = "F";
        }
        return grade;
    }
}
