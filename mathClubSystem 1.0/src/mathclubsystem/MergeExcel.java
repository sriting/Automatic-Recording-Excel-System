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

public class MergeExcel extends JFrame implements ActionListener{
    JPanel jpMergeTitle,jpMergeUseTip,jpMergeButton;
    JLabel jlMergeTitle,jlMergeUseTip1,jlMergeUseTip2,jlMergeUseTip3,jlMergeUseTip4;
    JButton jbMergeExcel,jbMergeReturn;
    ExcelMerger em = new ExcelMerger();
    
    public static void main(String[] args){
        MergeExcel merge = new MergeExcel();
    }
    public MergeExcel(){
        //创建组件
        jlMergeTitle = new JLabel("合并表格");
        jlMergeUseTip1 = new JLabel("注意事项：1.这个excel文件名必须是'mathclubMember.xls'，注意后缀为xls，其余格式都会导致程序出错的！");
        jlMergeUseTip2 = new JLabel("2.两个表格应同放在该excel文件里,表一名为sheet1，表二sheet2，且该文件要放在src文件夹的mathclubsystem内");
        jlMergeUseTip3 = new JLabel("3.两个表格内的每个列名和格式请参照样表mathclubMember，列的顺序和名称请按照样表设置，否则会出错！");       
        jlMergeUseTip4 = new JLabel("4.合并的内容会写进mathclubMember这个excel，以防万一请提前备份，否则记录会被覆盖；重复记录在最后一列");
        
        jpMergeTitle = new JPanel();
        jpMergeTitle.setPreferredSize(new Dimension(700,20));
        jpMergeUseTip = new JPanel();
        jpMergeUseTip.setPreferredSize(new Dimension(700,70));
        jpMergeButton = new JPanel();
        jpMergeButton.setPreferredSize(new Dimension(700,100));
        
        jbMergeExcel = new JButton("合并两个excel表格");
        jbMergeReturn = new JButton("返回主页");
        jbMergeExcel.addActionListener(this);//添加监听
        jbMergeReturn.addActionListener(this);
        
        jpMergeTitle.add(jlMergeTitle);
        jpMergeUseTip.add(jlMergeUseTip1);
        jpMergeUseTip.add(jlMergeUseTip2);
        jpMergeUseTip.add(jlMergeUseTip3);        
        jpMergeUseTip.add(jlMergeUseTip4);
        
        jpMergeButton.add(jbMergeExcel);
        jpMergeButton.add(jbMergeReturn);
        
        this.setLayout(new GridLayout(3,1));
        this.add(jpMergeTitle);
        this.add(jpMergeUseTip);
        this.add(jpMergeButton);
        
        ExcelMerger em = new ExcelMerger();
        
        //设置布局管理器
        this.setTitle("合并excel表格");
        this.setSize(700,400);
        this.setLocationRelativeTo(null);//屏幕居中显示
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//退出a关闭JFrame,且保证JVM也推出
        this.setVisible(true);//显示窗体
    }
    
    public void actionPerformed(ActionEvent e){
        boolean isMerged = false;
        if(e.getActionCommand()=="合并两个excel表格"){
            try {
                isMerged=ExcelMerger.excelMerge();
            } catch (IOException ex) {
                Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(isMerged){
                JOptionPane.showMessageDialog(null,"合并成功！","提示消息",JOptionPane.WARNING_MESSAGE);   
            }else{
                JOptionPane.showMessageDialog(null,"合并失败！请按照使用说明进行合并！","提示消息",JOptionPane.ERROR_MESSAGE);
            }
            dispose();
            MathClubSystem backMain = new MathClubSystem();
        }else if(e.getActionCommand()=="返回主页"){
            dispose();
            MathClubSystem backMain = new MathClubSystem();
        }
    }
}
