package com.book.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mo on 2016/12/14.
 */
public class Cart {
    private List<OrderItem> list;

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public Cart(){
        list = new ArrayList<>();
    }

    public Boolean add(OrderItem record){
        int index = this.indexOf(record.getBid());//查找待插入的记录在列表中的位置
        if(index != -1){//若待插入的记录已存在,叠加数量
            list.get(index).setQuantity(list.get(index).getQuantity() + record.getQuantity());
        }
        else//若待插入的记录不存在，则插入记录
            list.add(record);
        return true;
    }

    public Boolean remove(int bid){
        int index = this.indexOf(bid);//查找待删除的记录在列表中的位置
        if(index == -1) return false;//若不存在返回false表示删除失败
        else {//否则
            list.remove(index);
            return true;
        }
    }

    public void removeAll(){
        list.clear();
    }

    //查找记录在列表中的位置
    public int indexOf(int bid){
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if(list.get(i).getBid().equals(bid)) return i;
        }
        return -1;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "list=" + list +
                '}';
    }
}
