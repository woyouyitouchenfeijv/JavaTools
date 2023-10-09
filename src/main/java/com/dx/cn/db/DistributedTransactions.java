package com.dx.cn.db;

import com.mysql.cj.jdbc.MysqlXid;

import javax.sql.XAConnection;
import javax.sql.XADataSource;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;

/**
 * @Author w y y t c f j
 * @Description DistributedTransactions
 * @Date 2023/10/8
 */
public class DistributedTransactions {


    public static void main(String[] args) throws Exception {
        XADataSource dataSource1 = DataSourceFactory.createXADataSource("localhost",3306,"ceshi","root","rootroot");
        XADataSource dataSource2 = DataSourceFactory.createXADataSource("localhost",3306,"ceshi","root","rootroot");
        // 获取 XA 数据库连接
        XAConnection connection1 = dataSource1.getXAConnection();
        XAConnection connection2 = dataSource2.getXAConnection();
        try {


            // 获取 XA 事务管理器
            XAResource xaResource1 = connection1.getXAResource();
            XAResource xaResource2 = connection2.getXAResource();

            // 启动分布式事务
            Xid xid1 = generateXid(1);
            Xid xid2 = generateXid(2);

            xaResource1.start(xid1, XAResource.TMNOFLAGS);
            xaResource2.start(xid2, XAResource.TMNOFLAGS);
            // 在两个数据库上执行相关操作
            // ...

            // 提交分布式事务
            xaResource1.end(xid1, XAResource.TMSUCCESS);
            xaResource2.end(xid2, XAResource.TMSUCCESS);

            int result1 = xaResource1.prepare(xid1);
            int result2 = xaResource2.prepare(xid2);

            if (result1 == XAResource.XA_OK && result2 == XAResource.XA_OK) {
                xaResource1.commit(xid1, false);
                xaResource2.commit(xid2, false);
                System.out.println("分布式事务提交成功！");
            } else {
                xaResource1.rollback(xid1);
                xaResource2.rollback(xid2);
                System.out.println("分布式事务回滚！");
            }

        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
        }finally {
            connection1.close();
            connection2.close();
        }
    }

    // 生成 Xid
    private static Xid generateXid(int branchId) {
        byte[] gid = new byte[1];
        gid[0] = (byte) 0;

        byte[] bid = new byte[1];
        bid[0] = (byte) branchId;
        return new MysqlXid( gid, bid,0x1234);
    }


}
