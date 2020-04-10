import java.io.*;
import java.util.*;

public class Reading_Data {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String row="";
		String[] dat = null;
		List<String> date=new ArrayList<>();
		List<String> dom_trans=new ArrayList<>();
		List<String> debit=new ArrayList<>();
		List<String> credit=new ArrayList<>();
		List<String> currency=new ArrayList<>();
		List<String> Name=new ArrayList<>();
		List<String> cur=new ArrayList<>();
		List<String> trans=new ArrayList<>();
		List<String> place=new ArrayList<>();
		String name=null;
		String trans_type=null;
		boolean col=false;
		
		                                                              // Write the Address of the Input File
		                                                              //ICICI-Input-Case2.csv
		                                                              //Axis-Input-Case3.csv
		BufferedReader csvReader = new BufferedReader(new FileReader("C:\\Users\\ADITYA\\Desktop\\download chrome\\HDFC-Input-Case1.csv"));
		while ((row = csvReader.readLine()) != null) {
		    dat = row.split(",");
		    String[] data=new String[dat.length];
		    for(int i=0;i<dat.length;i++)
		    {
		    	data[i]=dat[i].trim();
		    }
		    // do something with the data
		    List<String> l=Arrays.asList(data);
		    if(data.length>0 && l.contains("Date")==false&&l.contains("International Transactions")==false&&l.contains("Domestic Transactions")==false)
		    {
		    if(Arrays.asList(data).contains("Rahul")==true ||Arrays.asList(data).contains("Ritu")==true )
		    {
			    for(int i=0;i<data.length;i++)
			    {
			    	if(data[i].trim().equals("Rahul")==true) // Finding the Cardname whether it is ritu or rahul
			    	{
			    		name="Rahul";
			    	}
			    	if(data[i].trim().equals("Ritu")==true)
			    	{
			    		name="Ritu";
			    	}
			    }
		    }
		    else if(name!=null)
		    {
		    	boolean db=false;
		    	boolean cr=false;
			    for(int i=0;i<data.length;i++)
				{
					//System.out.print(data[i].trim()+" ** ");
			    	if(data[i].contains("-")==true)
			    	{
			    		date.add(data[i]);
			    	}
			    	else if(data[i].length()>1 && ((data[i].charAt(0)>='a'&&data[i].charAt(0)<='z')||(data[i].charAt(0)>='A'&&data[i].charAt(0)<='Z')))
			    	{
			    		String d=data[i];
			    		String curr=d.substring(d.length()-3,d.length());
			    		if(curr.equals("EUR")==true||curr.equals("USD")==true) //Checking for Currency 
			    		{
			    			cur.add(curr);
			    			d=d.substring(0,d.length()-3).trim();
			    		}
			    		else
			    		{
			    			cur.add("INR");
			    		}
			    		
			    		List<String> temp=Arrays.asList(d.split(" "));
			    		place.add(temp.get(temp.size()-1));
			    		//temp.remove(temp.size()-1);
			    		d="";
			    		for(int ii=0;ii<temp.size();ii++)
			    		{
			    			d=d+temp.get(ii)+" ";
			    		}
			    		//d=temp.toString();
			    		dom_trans.add(d);
			    	}
			    	else if(col==false&&data[i].length()>0){ // col is used to find we=hether the given file has different column for debit-credit or same
			    		String tt=data[i].trim();
			    		String t="";
			    		if(tt.length()>3)
			    		t=(tt.substring(tt.length()-2,tt.length()));
			    		if(t.equals("cr")==true)
			    		{
			    			credit.add(tt.substring(0,tt.length()-3));
			    			debit.add("0");
			    		}
			    		else
			    		{
			    			debit.add(tt);
			    			credit.add("0");
			    		}
			    	}
			    	else if(col==true)
			    	{
			    		if(data[i].length()==0||data[i].equals("0")==true)
			    		{
			    			if(db==false&&cr==false)
			    			{
			    				debit.add("0");
			    				db=true;
			    			}
			    			else if(db==false)
			    			{
			    				debit.add("0");
			    				db=true;
			    			}
			    			else if(cr==false)
			    			{
			    				credit.add("0");
			    				cr=true;
			    			}
			    		}
			    		else
			    		{
			    			if(db==false&&cr==false)
			    			{
			    				debit.add(data[i]);
			    				credit.add("0");
			    				cr=true;
			    				db=true;
			    			}
			    			else if(db==false)
			    			{
			    				debit.add(data[i]);
			    				db=true;
			    			}
			    			else
			    			{
			    				credit.add(data[i]);
			    				cr=true;
			    			}
			    		}
			    	}
				}
			    Name.add(name);
			    trans.add(trans_type);
			    //System.out.print(date+"   "+dom_trans+"  "+debit+"  "+credit+"  "+Name);
				//System.out.println();
			    //System.out.println();
		    }
		    }
		    //Finding whether transaction is Domestic Or International
		    else if(data.length>0 && (l.contains("International Transactions")==true||l.contains("Domestic Transactions")==true))
			{
				if(l.contains("International Transactions")==true)
				{
					trans_type="International";
				}
				else if(l.contains("Domestic Transactions")==true)
				{
					trans_type="Domestic";
				}
			}
		    
		    else if(data.length>0 && (l.contains("Debit")==true||l.contains("Credit")==true))
		    {
		    	col=true;
		    }
		    //System.out.println();
		}
		csvReader.close();
		
		for(int i=0;i<Name.size();i++) // Printing the data retrieved from input csv file
		{
			System.out.print(date.get(i)+"   "+dom_trans.get(i)+"  "+debit.get(i)+"  "+credit.get(i)+"  "+cur.get(i)+"  "+Name.get(i)+"  "+trans.get(i)+"  "+place.get(i));
			System.out.println();
		}
		
		
	//This section For sorting the data according to dates and then Writing to csv file	
		
		List<List<String>> rows = new ArrayList<>();
		List<datee> ob=new ArrayList<>();
		List<Date> dd=new ArrayList<>();
		for(int i=0;i<Name.size();i++)
		{
			//List<String> li=Arrays.asList(date.get(i),dom_trans.get(i),debit.get(i),credit.get(i),cur.get(i),Name.get(i),trans.get(i),place.get(i));
			//rows.add(li);
			datee o=new datee(date.get(i),debit.get(i),credit.get(i),trans.get(i),dom_trans.get(i),cur.get(i),Name.get(i),place.get(i));
			ob.add(o);
			String[] t=date.get(i).split("-");
			//Date r=new Date(2015,01,01);
			dd.add(new Date(Integer.parseInt(t[2])-1900,Integer.parseInt(t[1])-1,Integer.parseInt(t[0])));
		}
		
		
		Collections.sort(dd,new Comparator<Date>() {
			public int compare(Date o1,Date o2) {
				return o1.compareTo(o2);
			}
		});
		
		for(int i = 0; i < dd.size(); i++){
            System.out.println(dd.get(i).toString()+"****"); // Printing the dates after they are been sorted
        }
		
		for(int i=0;i<dd.size();i++)
		{
			if(dd.get(i)!=null)
			{
			int ww=find(dd.get(i),ob);
			datee w=ob.get(ww);
			ob.remove(ww);
			List<String> li=Arrays.asList(w.date,w.td,w.db,w.cr,w.cur,w.name,w.trans,w.loc);
			rows.add(li);
			System.out.println(li+"%%"); // Printing Sorted Data on Console
			}
		}
		
		//Making New File and Writing file
			FileWriter csvWriter = new FileWriter("OutPut_TestCase1.csv");
			csvWriter.append("Date");
			csvWriter.append(",");
			csvWriter.append("Transaction Description");
			csvWriter.append(",");
			csvWriter.append("Debit");
			csvWriter.append(",");
			csvWriter.append("Credit");
			csvWriter.append(",");
			csvWriter.append("Currency");
			csvWriter.append(",");
			csvWriter.append("CardName");
			csvWriter.append(",");
			csvWriter.append("Transaction");
			csvWriter.append(",");
			csvWriter.append("Location");
			csvWriter.append("\n");

			for (List<String> rowData : rows) {
			    csvWriter.append(String.join(",", rowData));
			    csvWriter.append("\n");
			}

			csvWriter.flush();
			csvWriter.close();

	}
	
	public static int find(Date tt,List<datee> l) //It will find the date form list of objects and return the index
	{
		for(int i=0;i<l.size();i++)
		{
			String t=l.get(i).d.toString();
			if(tt.toString().equals(t)==true)
			{
				return i;
			}
		}
		return -1;
	}

}

//Object to store and help in sorting
class datee
{
	String date,db,cr,trans,td,cur,name,loc;
	Date d;
	@SuppressWarnings("deprecation")
	datee(String date,String db,String cr,String trans,String td,String cur,String name,String loc)
	{
		this.date=date;
		this.db=db;
		this.cr=cr;
		this.trans=trans;
		this.td=td;
		this.cur=cur;
		this.name=name;
		this.loc=loc;
		String[] t=date.split("-");
		//Date r=new Date(2015,01,01);
		this.d=new Date(Integer.parseInt(t[2])-1900,Integer.parseInt(t[1])-1,Integer.parseInt(t[0]));
	}
}
