package _422.studentsystem.model;

import _422.studentsystem.bean.Student;
import _422.studentsystem.dao.ManageHelper;

import java.util.Vector;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class StudentModel extends AbstractTableModel {
	private ManageHelper helper;
	private Vector<Student> students;
	//rowData用来存放行数据
	//columnNames存放列名
	private  Vector<String> columnNames = null;
	private Vector<Vector<String>> rowData = null;
	
		
	 public StudentModel(String sql,JDialog jd) {
		helper = new ManageHelper();
		students = helper.getStudent(sql);
		
		columnNames = new Vector<String>();
		rowData = new Vector<Vector<String>>();
		columnNames.add("学号");
		columnNames.add("姓名");
		columnNames.add("性别");
		columnNames.add("年级");
		columnNames.add("班级");
		columnNames.add("院系");
		columnNames.add("专业");
		for(Student student : students){
			Vector<String> hang = new Vector<String>();
			hang.add(student.getStudent_ID());
			hang.add(student.getStudent_Name());
			hang.add(student.getSex());
			hang.add(student.getGrade());
			hang.add(student.getClasse());
			hang.add(student.getDepartment_Name());
			hang.add(student.getMajor_Name());
			rowData.add(hang);
		}
		if(getRowCount()!=0){
			JOptionPane.showMessageDialog(jd, "一共有"+getRowCount()+"条记录！");
			return ;
		}else{
			JOptionPane.showMessageDialog(jd, "没有任何记录！");
			return ;
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
