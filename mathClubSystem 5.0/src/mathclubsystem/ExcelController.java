
package mathclubsystem;
/**
 *
 * @author 蔡佳人
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

public class ExcelController {
    
    @SuppressWarnings("deprecation")//1是线代，2是微积分
    public static boolean setRegister(String id,int stage,int book)throws FileNotFoundException, IOException{

        String filename1= System.getProperty("user.dir");
        String filename= filename1+"/excel/mathclubMember.xls";//路径建议不要和class放在同级，最好另外放一个文件夹如excel，否则构建成jar包时excel表格一起编译进去，表格内容就不可更改了
        FileInputStream fin = new FileInputStream(filename);
        HSSFWorkbook workbook = new HSSFWorkbook(fin);
        HSSFSheet sheet = workbook.getSheetAt(0);
        HSSFRow row;
        HSSFCell cell;
        String cellstr=null;
        int cellstrd;
        String yes = "yes";
        String booka=null, bookb=null;
        int bookrow=5+book+(stage-1)*2;//book:1 现代，2 微积分 3 两本
        int bookrow2=7+(stage-1)*2;
        if(book==3){
            bookrow=6+(stage-1)*2; 
        }
        
        boolean isSetted = false;
        //workbook = new HSSFWorkbook(new FileInputStream(filename));
        //按名引用excel工作表
        //HSSFSheet sheet = workbook.getSheet("JSP");
        //也可以用以下方式来获取excel的工作表，采用工作表的索引值
        //sheet = workbook.getSheetAt(0);
        for(int icount=1;sheet.getRow(icount)!=null;icount++){ //从1开始是略过了类名
            row = sheet.getRow(icount);
            cell= row.getCell(0);//单元格也是从0开始的
            if(cell!=null){
                if(cell.getCellType()==HSSFCell.CELL_TYPE_NUMERIC){ //这样防止id单元格为数字格式的不能被检索出来
                    cellstrd = (int)cell.getNumericCellValue();
                    cellstr = Integer.toString(cellstrd);
                }else if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC){
                    cellstrd = (int)cell.getNumericCellValue();
                    cellstr = Integer.toString(cellstrd);
                }else{
                    cellstr = cell.getStringCellValue();
                }
            }
            if(cellstr.equals(id)){
                if(row.getCell(bookrow)!=null){
                    booka = row.getCell(bookrow).toString();
                }
                if(row.getCell(bookrow2)!=null){
                    bookb = row.getCell(bookrow2).toString();
                }
                if(book!=3 && !yes.equals(booka)){
                    getCell(row,bookrow).setCellValue("yes");
                    isSetted = true;
                }
                
                if(book==3 && (yes.equals(booka)||yes.equals(bookb))){
                    isSetted = false;
                }else if(book== 3 && (!yes.equals(booka)&& !yes.equals(bookb))){
                    if(!yes.equals(bookb)){
                        getCell(row,bookrow2).setCellValue("yes");
                        isSetted = true;
                    }
                    if(!yes.equals(booka)){
                        getCell(row,bookrow).setCellValue("yes");
                        isSetted = true;
                    }
                }
                //另一种比较法
//                if(book==3){
//                    if(row.getCell((short)bookrow) != null||row.getCell((short)bookrow2) != null){
//                        isSetted = false;
//                    }else{
//                        getCell(row,bookrow).setCellValue("yes");                     
//                        getCell(row,bookrow2).setCellValue("yes");
//                        isSetted = true;
//                    }
//                }else{
//                    if(row.getCell((short)bookrow) == null){
//                        getCell(row,bookrow).setCellValue("yes");
//                        isSetted = true;
//                    }
//                }
            booka=null;bookb=null;
            }           
        }
        //打印读取值
        //System.out.println(cell.getStringCellValue());
    
        //新建一输出流
        FileOutputStream fout = new FileOutputStream(filename);  //PS：filename 是你另存为的路径，不处理直接写入模版文件
        //存盘
        workbook.write(fout);      
        fout.flush();
        //结束关闭
        fout.close();
        fin.close();
        return isSetted;
    }
    
    public static String getRegister(String id,int stage) throws FileNotFoundException, IOException{

        String register = "否";    
        String filename1= System.getProperty("user.dir");
        String filename= filename1+"/excel/mathclubMember.xls";
        FileInputStream fin = new FileInputStream(filename);
        HSSFWorkbook workbook = new HSSFWorkbook(fin);
        HSSFSheet sheet = workbook.getSheetAt(0);
        HSSFRow row;
        HSSFCell cell;
        String cellstr=null;
        int cellstrd;
        int book1=6,book2=7;
        String booka=null,bookb=null;
        String yes = "yes";
        
        for(int icount=1;sheet.getRow(icount)!=null;icount++){ //从1开始是略过了类名
            row = sheet.getRow(icount);
            cell= row.getCell(0);//单元格也是从0开始的
            if(cell!=null){
                if(cell.getCellType()==HSSFCell.CELL_TYPE_NUMERIC){ //这样防止id单元格为数字格式的不能被检索出来
                    cellstrd = (int)cell.getNumericCellValue();
                    cellstr = Integer.toString(cellstrd);
                }else if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC){
                    cellstrd = (int)cell.getNumericCellValue();
                    cellstr = Integer.toString(cellstrd);
                }else{
                    cellstr = cell.getStringCellValue();
                }
            }
            if(cellstr.equals(id)){
                book1=6+(stage-1)*2;book2=7+(stage-1)*2;//列数
                if(row.getCell(book1)!=null){
                    booka = row.getCell(book1).toString();
                }
                if(row.getCell(book2)!=null){
                    bookb = row.getCell(book2).toString();
                }
                if(yes.equals(booka) && yes.equals(bookb)){
                    register = "两本都领过！";               
                }else if(yes.equals(booka) && !yes.equals(bookb)){
                    register = "领过 |线代|";
                }else if(!yes.equals(booka) && yes.equals(bookb)){
                    register = "领过 |微积分|";
                }
            }           
            booka=null;bookb=null;
        }              
        fin.close();
        return register;
    }
    
    public static String sumBook1(int stage) throws FileNotFoundException, IOException{

        int sum = 0;    
        String filename1= System.getProperty("user.dir");
        String filename= filename1+"/excel/mathclubMember.xls";
        FileInputStream fin = new FileInputStream(filename);
        HSSFWorkbook workbook = new HSSFWorkbook(fin);
        HSSFSheet sheet = workbook.getSheetAt(0);
        HSSFRow row;
        HSSFCell cell;
        int book1=6+(stage-1)*2;
        String yes = "yes";
        String thisbook=null;
        
        for(int icount=1;sheet.getRow(icount)!=null;icount++){ //从1开始是略过了类名
            row = sheet.getRow(icount);       
            if(row.getCell(book1)!=null){
                thisbook = row.getCell(book1).toString();
            }
            if(yes.equals(thisbook)){
                sum+=1;
            }                      
            thisbook = null;
        }              
        fin.close();
        String sums = sum+"";
        return sums;
    }
    
    public static String sumBook2(int stage) throws FileNotFoundException, IOException{

        int sum = 0;    
        String filename1= System.getProperty("user.dir");
        String filename= filename1+"/excel/mathclubMember.xls";
        FileInputStream fin = new FileInputStream(filename);
        HSSFWorkbook workbook = new HSSFWorkbook(fin);
        HSSFSheet sheet = workbook.getSheetAt(0);
        HSSFRow row;
        HSSFCell cell;
        int book2=7+(stage-1)*2;
        String yes = "yes";
        String thisbook=null;
        
        for(int icount=1;sheet.getRow(icount)!=null;icount++){ //从1开始是略过了类名
            row = sheet.getRow(icount);         
            if(row.getCell(book2)!=null){
                thisbook = row.getCell(book2).toString();
            }
            if(yes.equals(thisbook)){
                sum+=1;
            }   
            thisbook =null;
        }              
        fin.close();
        String sums = sum+"";
        return sums;
    }

    public static String sumTwoBook(int stage) throws FileNotFoundException, IOException{

        int sum = 0;    
        String filename1= System.getProperty("user.dir");
        String filename= filename1+"/excel/mathclubMember.xls";
        FileInputStream fin = new FileInputStream(filename);
        HSSFWorkbook workbook = new HSSFWorkbook(fin);
        HSSFSheet sheet = workbook.getSheetAt(0);
        HSSFRow row;
        HSSFCell cell;
        int book2=7+(stage-1)*2,book1=6+(stage-1)*2;
        String booka=null,bookb=null;
        String yes = "yes";
        
        for(int icount=1;sheet.getRow(icount)!=null;icount++){ //从1开始是略过了类名
            row = sheet.getRow(icount);  
            if(row.getCell(book1)!=null){
                booka = row.getCell(book1).toString();
            }
            if(row.getCell(book2)!=null){
                bookb = row.getCell(book2).toString();
            }
            
            if(yes.equals(booka)){
                sum+=1;
            }
            if(yes.equals(bookb)){
                sum+=1;
            }                        
            booka=null;bookb=null;
        }              
        fin.close();
        String sums = sum+"";
        return sums;
    }
    
    public static String getName(String id,int stage) throws FileNotFoundException, IOException{

        String name = null;
        String filename1= System.getProperty("user.dir");
        String filename= filename1+"/excel/mathclubMember.xls";
        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(filename));
        HSSFSheet sheet = workbook.getSheetAt(0);
        HSSFRow row;
        HSSFCell cell;
        int cellstrd;
        String cellstr=null;
        
        for(int icount=1;sheet.getRow(icount)!=null;icount++){ //从1开始是略过了类名
            row = sheet.getRow(icount);
            cell= row.getCell(0);//单元格也是从0开始的
            if(cell!=null){
                if(cell.getCellType()==HSSFCell.CELL_TYPE_NUMERIC){ //这样防止id单元格为数字格式的不能被检索出来
                    cellstrd = (int)cell.getNumericCellValue(); //因为excel的数字类型单元格取过来默认是double，所以要转成int！
                    //System.out.println(cellstrd);
                    cellstr = Integer.toString(cellstrd);
                }else if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC){
                    cellstrd = (int)cell.getNumericCellValue();
                    cellstr = Integer.toString(cellstrd);
                }else{
                    cellstr = cell.getStringCellValue();
                }
            }
            if(cellstr.equals(id)){
                name = row.getCell(1).toString();
            }           
        }
        
        if(name==null){
            name="查无此人！";
        }
        return name;
    }
    
    public static String getSubject(String id,int stage) throws FileNotFoundException, IOException{

        String sub = null;
        String filename1= System.getProperty("user.dir");
        String filename= filename1+"/excel/mathclubMember.xls";
        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(filename));
        HSSFSheet sheet = workbook.getSheetAt(0);
        HSSFRow row;
        HSSFCell cell;
        int cellstrd;
        String cellstr=null;
        
        for(int icount=1;sheet.getRow(icount)!=null;icount++){ //从1开始是略过了类名
            row = sheet.getRow(icount);
            cell= row.getCell(0);//单元格也是从0开始的
            if(cell!=null){
                if(cell.getCellType()==HSSFCell.CELL_TYPE_NUMERIC){ //这样防止id单元格为数字格式的不能被检索出来
                    cellstrd = (int)cell.getNumericCellValue();
                    cellstr = Integer.toString(cellstrd);
                }else if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC){
                    cellstrd = (int)cell.getNumericCellValue();
                    cellstr = Integer.toString(cellstrd);
                }else{
                    cellstr = cell.getStringCellValue();
                }
            }
            if(cellstr.equals(id)){
                sub = row.getCell(2).toString();
            }           
        }
        
        if(sub==null){
            sub="查无此人！";
        }
        return sub;
    }

    public static HSSFCell getCell(HSSFRow row, int index) {

        // 取得分发日期单元格
        HSSFCell cell = row.getCell(index);

        // 如果单元格不存在
        if (cell == null) {
            // 创建单元格
            cell = row.createCell(index);
        }
        // 返回单元格
        return cell;
    }

    public static void main(String[] args) throws IOException {
        ExcelController ec = new ExcelController();
        String ID = "";
        try {
            ec.setRegister(ID,1,1);
        }catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
