package _422.studentsystem.model;

import _422.studentsystem.bean.AnalyzeResult;
import _422.studentsystem.bean.Student;
import _422.studentsystem.dao.ManageHelper;

import java.util.HashMap;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class ScoreAnalyzeModel extends AbstractTableModel{
	private ManageHelper helper;
	private Vector<Student> students;
	private  Vector<String> columnNames = null;	//列名
	private Vector<Vector<String>> rowData = null;	//行数据

	public ScoreAnalyzeModel(String major_Name,String grade,String classe) {
		helper = new ManageHelper();
		Vector<String> courses = helper.getCourse(helper.getAllMajor().get(major_Name),grade);//获得课程
		Vector<AnalyzeResult> results = helper.analyzeScore(major_Name, classe, grade);	//得到分析的结果集合
		
		columnNames = new Vector<String>();
		rowData = new Vector<Vector<String>>();
		columnNames.add("学号");
		columnNames.add("姓名");
		for(int i=0;i<courses.size();i++){
			columnNames.add(courses.get(i));
		}
		columnNames.add("总成绩");
		columnNames.add("平均成绩");
		columnNames.add("排名");
		for(int i=0;i<results.size();i++){
			Vector<String> hang = new Vector<String>();
			AnalyzeResult result = results.get(i);
			hang.add(result.getStudentId());
			hang.add(result.getStudentName());
			HashMap<String, String> scores = helper.getStudentScore(result.getStudentId());	//得到所有课程
			for(int j=0;j<scores.size();j++){
				String score = scores.get(courses.get(j));	//得到成绩
				hang.add(score);
			}
			hang.add(result.getSumScore());
			String avg = result.getAvgScore();
			hang.add(avg.charAt(0)+""+avg.charAt(1)+""+avg.charAt(2)+""+avg.charAt(3));
			hang.add(getRowCount()+1+"");
			rowData.add(hang);
		}
	}
	
		//得到共有多少行
		@Override
		public int getRowCount() {
			return this.rowData.size();
		}
		//得到共有多少列
		@Override
		public int getColumnCount() {
			return this.columnNames.size();
		}
		//得到某行某列的数据
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			return ((Vector)this.rowData.get(rowIndex)).get(columnIndex);
		}
		//重写方法 getColumnName
		@Override  
		public String getColumnName(int column) {
			return (String)this.columnNames.get(column);
		}
}
