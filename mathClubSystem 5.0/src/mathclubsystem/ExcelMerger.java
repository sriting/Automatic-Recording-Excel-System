package mathclubsystem;
/**
 *
 * @author 蔡佳人
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelMerger {
    
    @SuppressWarnings("deprecation")
    public static boolean excelMerge()throws FileNotFoundException, IOException{

        String filename1= System.getProperty("user.dir");
        String filename= filename1+"/excel/mathclubMember.xls";
        boolean isMerged = true;
        FileInputStream fin1 = new FileInputStream(filename);
        HSSFWorkbook workbook1 = new HSSFWorkbook(fin1);
        HSSFSheet sheet1 = workbook1.getSheetAt(0);
        HSSFSheet sheet2 = null;
        HSSFRow row,row2;
        HSSFCell cell,cell2;   
        String excelrow1=null,excelrow2=null;
        String yes = "yes";
        //System.out.println(workbook1.getNumberOfSheets());
        if(workbook1.getNumberOfSheets()<2){ //防止要是sheet2为空造成空指针
            isMerged =false;
        }else{  
            sheet2 = workbook1.getSheetAt(1);
            for(int icount=1;sheet1.getRow(icount)!=null&&sheet2.getRow(icount)!=null;icount++){ //从1开始是略过了类名
                row = sheet1.getRow(icount);
                row2 = sheet2.getRow(icount);
                cell = row.getCell(0);//单元格也是从0开始的
                cell2 = row2.getCell(0);
                if(cell != null && cell2!= null){
                    if(cell.toString().equals(cell2.toString())){
                        for(int irow=6;irow<14;irow++){
                            if(row.getCell(irow)!=null){
                                excelrow1 = row.getCell(irow).getStringCellValue();
                            }
                            if(row2.getCell(irow)!=null){
                                excelrow2 =  row2.getCell(irow).getStringCellValue();
                            }
                            if(yes.equals(excelrow2) && !yes.equals(excelrow1)){
                                getCell(row,irow).setCellValue("yes");
                            }else if(yes.equals(excelrow2) && yes.equals(excelrow1)){
                                getCell(row,14).setCellValue("是");
                            }
                            excelrow1=null;excelrow2=null;//记得每次循环要清空！
                        }            
                    }           
                }
            }
            //打印读取值
            //System.out.println(cell.getStringCellValue());

            //新建一输出流
            FileOutputStream fout1 = new FileOutputStream(filename);  //PS：filename 是你另存为的路径，不处理直接写入模版文件
            //存盘
            workbook1.write(fout1);      
            fout1.flush();
            //结束关闭
            fout1.close();
        }
        fin1.close();
        return isMerged;
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
        ExcelMerger ec = new ExcelMerger();
        try {
            ec.excelMerge();
        }catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
