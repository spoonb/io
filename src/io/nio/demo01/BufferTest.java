package io.nio.demo01;

import java.nio.ByteBuffer;

public class BufferTest {

    public static void main(String[] args) {
        // 测试数据
        String test = "spoonb's blog";

        // 分配一个指定大小(16)的字节缓冲区(当前写模式)
        // 客户端 <---> 缓冲区 <---> 直接缓冲区 <---> 缓冲区 <---> 服务端
        ByteBuffer ib = ByteBuffer.allocate(16);
        // 各参数初始值
        System.out.println("========== allocate(int) ==========");
        System.out.printf("position -> %d\n", ib.position()); // 下个元素的位置，0
        System.out.printf("limit -> %d\n", ib.limit()); // 缓冲区限制，16
        System.out.printf("capacity -> %d\n\n", ib.capacity()); // 缓冲区容量，16

        // 写入数据到上述缓冲区
        ib.put(test.getBytes());
        // 各参数值
        System.out.println("========== put(byte[]) ==========");
        System.out.printf("position -> %d\n", ib.position()); // 下个元素的位置，13
        System.out.printf("limit -> %d\n", ib.limit()); // 缓冲区限制，16
        System.out.printf("capacity -> %d\n\n", ib.capacity()); // 缓冲区容量，16

        // 取出一个数据(无参)(写模式)
        byte num1 = ib.get(); // 无参时只取一个元素
        // 各参数值
        System.out.println("========== get() ==========");
        System.out.printf("num1 -> %d\n", num1); // 取出的num1，0(不应该是s吗)
        System.out.printf("position -> %d\n", ib.position()); // 下个元素的位置，14
        System.out.printf("limit -> %d\n", ib.limit()); // 缓冲区限制，16
        System.out.printf("capacity -> %d\n\n", ib.capacity()); // 缓冲区容量，16

        // 修改缓冲区限制
        ib.limit(15); // limit -> 15
        // 各参数值
        System.out.println("========== limit(int) ==========");
        System.out.printf("position -> %d\n", ib.position()); // 下个元素的位置，14
        System.out.printf("limit -> %d\n", ib.limit()); // 缓冲区限制，15
        System.out.printf("capacity -> %d\n\n", ib.capacity()); // 缓冲区容量，16

        // 取出一个数据(无参)
        byte[] tmp = new byte[ib.limit()]; // 超过limit，BufferUnderflowException
        // ib.get(tmp); // 取出与tmp等长的元素

        // 取出给定长度的数据(写模式)
        tmp = new byte[ib.remaining()]; // remaining()获取剩余元素的个数，取剩余的所有元素
        ib.get(tmp); // 取出与tmp等长的元素
        // 各参数值
        System.out.println("========== get(byte[]) ==========");
        System.out.printf("tmp -> %s\n", new String(tmp, 0, tmp.length)); // 怎么是空字符串
        System.out.printf("position -> %d\n", ib.position()); // 下个元素的位置，15
        System.out.printf("limit -> %d\n", ib.limit()); // 缓冲区限制，15
        System.out.printf("capacity -> %d\n\n", ib.capacity()); // 缓冲区容量，16

        // 转为读模式
        num1 = ib.flip().get(); // limit -> position，position -> 0
        ib.mark(); // 标记当前状态
        // 各参数值
        System.out.println("========== get() ==========");
        System.out.printf("num1 -> %c\n", num1); // 取出的num1，s
        System.out.printf("position -> %d\n", ib.position()); // 下个元素的位置，1
        System.out.printf("limit -> %d\n", ib.limit()); // 缓冲区限制，15
        System.out.printf("capacity -> %d\n\n", ib.capacity()); // 缓冲区容量，16

        // 指定开始位置和长度读取
        ib.get(tmp, 0, tmp.length);
        // 各参数值
        System.out.println("========== get(byte[], off, len) ==========");
        System.out.printf("num1 -> %s\n", new String(tmp, 0, tmp.length)); // 取出的num1，s
        System.out.printf("position -> %d\n", ib.position()); // 下个元素的位置，2
        System.out.printf("limit -> %d\n", ib.limit()); // 缓冲区限制，15
        System.out.printf("capacity -> %d\n\n", ib.capacity()); // 缓冲区容量，16

        // 取出给定长度的数据(写模式)
        ib.position(1); // position -> 0，当mark > position时，mark -> -1
        tmp = new byte[ib.remaining()]; // remaining()获取剩余元素的个数，取剩余的所有元素
        ib.get(tmp); // 取出与tmp等长的元素
        // 各参数值
        System.out.println("========== get(byte[]) ==========");
        System.out.printf("tmp -> %s\n", new String(tmp, 0, tmp.length));
        System.out.printf("position -> %d\n", ib.position()); // 下个元素的位置，15
        System.out.printf("limit -> %d\n", ib.limit()); // 缓冲区限制，15
        System.out.printf("capacity -> %d\n\n", ib.capacity()); // 缓冲区容量，16

        // 回复到mark位置
        ib.reset(); // 无重载，mark备份只有一个版本，也只能回到这个版本
        // 各参数值
        System.out.println("========== reset() ==========");
        System.out.printf("position -> %d\n", ib.position()); // 下个元素的位置，1
        System.out.printf("limit -> %d\n", ib.limit()); // 缓冲区限制，15
        System.out.printf("capacity -> %d\n\n", ib.capacity()); // 缓冲区容量，16

        // 重置position和limit
        ib.clear(); // position -> 0，limit -> capacity
        // 各参数值
        System.out.println("========== clear() ==========");
        System.out.printf("position -> %d\n", ib.position()); // 下个元素的位置，0
        System.out.printf("limit -> %d\n", ib.limit()); // 缓冲区限制，16
        System.out.printf("capacity -> %d\n\n", ib.capacity()); // 缓冲区容量，16

        // 回复到mark位置
        // ib = ib.reset(); // 因为clear会将mark -> -1，InvalidMarkException
    }
}
