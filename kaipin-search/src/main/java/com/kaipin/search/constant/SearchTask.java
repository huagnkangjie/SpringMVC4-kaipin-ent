package com.kaipin.search.constant;

public final class SearchTask {

    public interface ObjType {
        // 对象类型(0-公司,1-职位,2-视频 3-学生 4-学校
        public final static byte company = 0x00;

        public final static byte position = 0x01;

        public final static byte live = 0x02;

        public final static byte student = 0x03;

        public final static byte school = 0x04;



    }

    public interface OptType {
        // 操作类型(0-add,1-delete,2-update

        public byte add = 0x00;
        public byte delete = 0x01;
        public byte update = 0x02;

    }

    public interface StatusType {
        // 处理状态（0-未处理,1-已处理
        public byte un_handle = 0x00;

        public byte handled = 0x01;

    }

    public static void main(String[] args) {
        System.out.println((ObjType.live|OptType.add));
        System.out.println((ObjType.live| OptType.update));
        System.out.println((ObjType.live| OptType.delete));
        // System.out.println(OptType.delete);
        // System.out.println(OptType.delete.ordinal());
    }
}
