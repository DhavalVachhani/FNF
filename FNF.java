import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class FNF
{
	public static void main(String[] args) {
		int ch;
		Scanner sc=new Scanner(System.in);
		
	int f=-1;
		
		
	FileManger fm=new FileManger();
	
	try {
		ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("obj.txt"));
		oos.writeObject(null);
	} catch (Exception e) {
		// TODO: handle exception
	}
		
		while(true)
		{
			System.out.println("\n\n1.Add Record\n2.Read\n3.Convert to FNF\n4.Exit");
			System.out.print("Enter your choice: ");
			ch=sc.nextInt();
			System.out.println("");
				switch(ch)
				{
				case 1: f=1;
					fm.add();
					break;
				case 2: fm.read();
					break;
				case 3:if(f==1)
					{
						fm.FNF();
						f=0;
					}
					
					else if(f==-1)
					{
						System.out.print("First Enter data");
					}
					else
					{
						System.out.print("Alredy in First Normal Form(FNF)");	
					}
					break;
		
				case 4: System.exit(0);
					
				}
		}
	}
}


class FileManger
{
	BufferedWriter bw;
	int n;
	Scanner sc=new Scanner(System.in);
	
	
	
	public FileManger() {
		
		try {
			bw=new BufferedWriter(new FileWriter("File.txt"));
			bw.write("Name\t\tRollNo\t\tSubjects");
			bw.newLine();
			bw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void add()
	{
		String roll,name,sub;
		ArrayList<Student> al=new ArrayList<>();
		
		
		System.out.println("Enter Name: ");
		name=sc.next();
		
		System.out.println("Enter Roll: ");
		roll=sc.next();
		
		System.out.println("Enter number of subjects: ");
		n=sc.nextInt();
		
		
		
			for (int i = 0; i<n; i++) {
				System.out.println("Enter Subject"+(i+1)+" :");
				sub=sc.next();
				
				Student st=new Student(name, roll, sub);
				al.add(st);
				
			}
			WriteObjectFile(al);
		WriteFile(al);
	
	}
	
	////////////////////////////////////////////////////////////////////////////
	
	public void read()
	{
		try {
			
			
			File f=new File("File.txt");
			Scanner s=new Scanner(f);
			
			while(s.hasNext())
			{
				System.out.println(s.nextLine());
			}
		
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	/////////////////////////////////////////////////////////////////////////////////////////
	
	
	public void FNF()
	{
		try {
			
		new FileManger();
			ArrayList<Student> al=new ArrayList<>();
			
			al=readObjectFile();
			
			Iterator<Student> i=al.iterator();
			
			while(i.hasNext())
			{
				Student st=i.next();
				bw.write(st.name+"\t\t"+st.roll+"\t\t"+st.sub);
				bw.newLine();
				bw.flush();
			}
		System.out.print("Succesfully Converted to FNF you can check by Pressing 2");	

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	
	///////////////////////////////////////////////////////////////////////////////////
	public void WriteObjectFile(ArrayList<Student> al)
	{
		try {
			
		ArrayList<Student> al1=new ArrayList<>(al);
		ArrayList<Student> al2;
		
		al2=readObjectFile();
		
		ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("obj.txt"));
			if(al2!=null)
			{
				al2.addAll(al1);
				oos.writeObject(al2);
				System.out.println("Ok");
				return;
			}
			
			oos.writeObject(al1);
			
		
			oos.close();
		} catch (Exception e) {
			System.out.println("Problem");
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<Student> readObjectFile()
	{
		ArrayList<Student> arr=null;
		try {
			
			ObjectInputStream ois=new ObjectInputStream(new FileInputStream("obj.txt"));
			
			arr=(ArrayList<Student>)ois.readObject();
			
			ois.close();
			
		} catch (Exception e) {
			System.out.println("Problem");
			e.printStackTrace();
		}	
		
		return arr;
	}
	
	
	
	public void WriteFile(ArrayList<Student> al)
	{
		try {
			
		
		Iterator<Student> i=al.iterator();
		
		while(i.hasNext())
		{
			Student st=i.next();
			bw.write(st.name+"\t\t"+st.roll+"\t\t"+st.sub);
				for (int j = 0; j <n-1; j++) {
					st=i.next();
					bw.write(","+st.sub);
				
					bw.flush();
				}
				bw.newLine();
		}
		
		
		} catch (Exception e) {
			// TODO: handle exception
		}
 	}
}


class Student implements Serializable
{
	String roll,name,sub;
	
	public Student(String name,String roll, String sub) {
		 this.roll=roll;
		 this.name=name;
		 this.sub=sub;
	}
}

