package mathclubsystem;

/**
 *
 * @author 蔡佳人
 */
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class DataAnalysis extends JFrame implements ActionListener{
    JPanel jpDataTitle,jpDataUseTip,jpDataInfo,jpDataReturn;
    JLabel jlDataTitle,jlDataUseTip1,jlDataUseTip2,jlDataStage,jlDataInfobook1,jlDataInfobook2,jlDataInfototal,jlDatabook1,jlDatabook2,jlDatatotal;
    JButton jbMergeReturn;
    ExcelMerger em = new ExcelMerger();//!!
    int stage=1;
    
    public static void main(String[] args) throws IOException{
        int st = 1;
        DataAnalysis merge = new DataAnalysis(st);
    }
    public DataAnalysis(int st) throws IOException{
        stage = st;
        //创建组件
        jlDataTitle = new JLabel("数据分析");
        jlDataUseTip1 = new JLabel("注意事项：1.数据分析只针对'mathclubMember.xls'这个文件中的第一个表sheet1内的数据进行统计");
        jlDataUseTip2 = new JLabel("2.线代数量、微积分数量和总数都根据主页选的时间统计的，如果想查看别的时间的数据分析请返回");
        jlDataInfobook1 = new JLabel("线代总数：");       
        jlDataInfobook2 = new JLabel("微积分总数：");
        jlDataInfototal = new JLabel("二者总数：");
        jlDatabook1 = new JLabel(ExcelController.sumBook1(stage));       
        jlDatabook2 = new JLabel(ExcelController.sumBook2(stage));
        jlDatatotal = new JLabel(ExcelController.sumTwoBook(stage));
        if(stage==1){
            jlDataStage = new JLabel("  时间： 上学期期中");
        }else if(stage==2){
            jlDataStage = new JLabel(  "时间： 上学期期末");
        }else if(stage==3){
            jlDataStage = new JLabel("  时间： 下学期期中");
        }else if(stage==4){
            jlDataStage = new JLabel("  时间： 下学期期末");
        }
        
        jpDataTitle = new JPanel();
        jpDataUseTip = new JPanel();
        jpDataInfo = new JPanel();
        jpDataReturn = new JPanel();
       
        jbMergeReturn = new JButton("返回主页");
        jbMergeReturn.addActionListener(this);
        
        jpDataTitle.add(jlDataTitle);
        jpDataUseTip.add(jlDataUseTip1);
        jpDataUseTip.add(jlDataUseTip2);
        jpDataInfo.add(jlDataInfobook1);   
        jpDataInfo.add(jlDatabook1); 
        jpDataInfo.add(jlDataInfobook2);
        jpDataInfo.add(jlDatabook2); 
        jpDataInfo.add(jlDataInfototal);
        jpDataInfo.add(jlDatatotal); 
        jpDataInfo.add(jlDataStage); 
        
        jpDataReturn.add(jbMergeReturn);
        
        this.setLayout(new GridLayout(4,1));
        this.add(jpDataTitle);
        this.add(jpDataUseTip);
        this.add(jpDataInfo);
        this.add(jpDataReturn);
  
        
        //设置布局管理器
        this.setTitle("数据分析");
        this.setSize(700,400);
        this.setLocationRelativeTo(null);//屏幕居中显示
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//退出a关闭JFrame,且保证JVM也推出
        this.setVisible(true);//显示窗体
    }
    
    public void actionPerformed(ActionEvent e){
        if(e.getActionCommand()=="返回主页"){
            dispose();
            MathClubSystem backMain = new MathClubSystem();
        }
    }    
}
