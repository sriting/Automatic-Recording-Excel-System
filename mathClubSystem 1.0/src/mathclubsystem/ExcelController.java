
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

public class ExcelController {
    
    @SuppressWarnings("deprecation")//1是线代，2是微积分
    public static boolean setRegister(String id,int stage,int book)throws FileNotFoundException, IOException{

        String filename1= System.getProperty("user.dir");
        String filename= filename1+"/mathclubMember.xls";
        FileInputStream fin = new FileInputStream(filename);
        HSSFWorkbook workbook = new HSSFWorkbook(fin);
        HSSFSheet sheet = workbook.getSheetAt(0);
        HSSFRow row;
        HSSFCell cell;
        int bookrow=4+book+(stage-1)*2;//book:1 现代，2 微积分 3 两本
        int bookrow2=6+(stage-1)*2;
        if(book==3){
            bookrow=5+(stage-1)*2; 
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
            if(cell.toString().equals(id)){
                if(book!=3 && row.getCell(bookrow) == null){
                    getCell(row,bookrow).setCellValue("yes");
                    isSetted = true;
                }
                
                if(book==3 && (row.getCell(bookrow) != null||row.getCell(bookrow2) != null )){
                    isSetted = false;
                }else if(book== 3 && (row.getCell(bookrow2) == null&&row.getCell(bookrow2) == null )){
                    if(row.getCell(bookrow2) == null){
                        getCell(row,bookrow2).setCellValue("yes");
                        isSetted = true;
                    }
                    if(row.getCell(bookrow) == null){
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
        String filename= filename1+"/mathclubMember.xls";
        FileInputStream fin = new FileInputStream(filename);
        HSSFWorkbook workbook = new HSSFWorkbook(fin);
        HSSFSheet sheet = workbook.getSheetAt(0);
        HSSFRow row;
        HSSFCell cell;
        int book1=5,book2=6;
        
        for(int icount=1;sheet.getRow(icount)!=null;icount++){ //从1开始是略过了类名
            row = sheet.getRow(icount);
            cell= row.getCell(0);//单元格也是从0开始的
            if(cell.toString().equals(id)){
                book1=5+(stage-1)*2;book2=6+(stage-1)*2;//列数
                if(row.getCell(book1) != null && row.getCell(book2) != null){
                    register = "两本都领过！";               
                }else if(row.getCell(book1) != null&&row.getCell(book2) == null){
                    register = "领过 |线代|";
                }else if(row.getCell(book1) == null&&row.getCell(book2) != null){
                    register = "领过 |微积分|";
                }
            }           
        }              
        fin.close();
        return register;
    }
    
    public static String sumBook1(int stage) throws FileNotFoundException, IOException{

        int sum = 0;    
        String filename1= System.getProperty("user.dir");
        String filename= filename1+"/mathclubMember.xls";
        FileInputStream fin = new FileInputStream(filename);
        HSSFWorkbook workbook = new HSSFWorkbook(fin);
        HSSFSheet sheet = workbook.getSheetAt(0);
        HSSFRow row;
        HSSFCell cell;
        int book1=5+(stage-1)*2;
        
        for(int icount=1;sheet.getRow(icount)!=null;icount++){ //从1开始是略过了类名
            row = sheet.getRow(icount);       
            if(row.getCell(book1) != null){
                sum+=1;
            }                      
        }              
        fin.close();
        String sums = sum+"";
        return sums;
    }
    
    public static String sumBook2(int stage) throws FileNotFoundException, IOException{

        int sum = 0;    
        String filename1= System.getProperty("user.dir");
        String filename= filename1+"/mathclubMember.xls";
        FileInputStream fin = new FileInputStream(filename);
        HSSFWorkbook workbook = new HSSFWorkbook(fin);
        HSSFSheet sheet = workbook.getSheetAt(0);
        HSSFRow row;
        HSSFCell cell;
        int book2=6+(stage-1)*2;
        
        for(int icount=1;sheet.getRow(icount)!=null;icount++){ //从1开始是略过了类名
            row = sheet.getRow(icount);         
            if(row.getCell(book2) != null){
                sum+=1;
            }   
        }              
        fin.close();
        String sums = sum+"";
        return sums;
    }

    public static String sumTwoBook(int stage) throws FileNotFoundException, IOException{

        int sum = 0;    
        String filename1= System.getProperty("user.dir");
        String filename= filename1+"/mathclubMember.xls";
        FileInputStream fin = new FileInputStream(filename);
        HSSFWorkbook workbook = new HSSFWorkbook(fin);
        HSSFSheet sheet = workbook.getSheetAt(0);
        HSSFRow row;
        HSSFCell cell;
        int book2=6+(stage-1)*2,book1=5+(stage-1)*2;
        
        for(int icount=1;sheet.getRow(icount)!=null;icount++){ //从1开始是略过了类名
            row = sheet.getRow(icount);           
            if(row.getCell(book2) != null){
                sum+=1;
            }
            if(row.getCell(book1) != null){
                sum+=1;
            }                        
        }              
        fin.close();
        String sums = sum+"";
        return sums;
    }
    
    public static String getName(String id,int stage) throws FileNotFoundException, IOException{

        String name = null;
        String filename1= System.getProperty("user.dir");
        String filename= filename1+"/mathclubMember.xls";
        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(filename));
        HSSFSheet sheet = workbook.getSheetAt(0);
        HSSFRow row;
        HSSFCell cell;
        
        for(int icount=1;sheet.getRow(icount)!=null;icount++){ //从1开始是略过了类名
            row = sheet.getRow(icount);
            cell= row.getCell(0);//单元格也是从0开始的
            if(cell.toString().equals(id)){
                name = row.getCell(1).toString();
            }           
        }
        
        if(name==null){
            name="查无此人！";
        }
        return name;
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
