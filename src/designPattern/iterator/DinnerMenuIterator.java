package designPattern.iterator;

import java.util.Iterator;

/**
 * Created by chenxiaoxue on 7/31/16.
 */
public class DinnerMenuIterator implements Iterator {
    MenuItem[] items;
    int position =0;

    public DinnerMenuIterator (MenuItem[] items){
        this.items = items;
    }



    @Override
    public Object next() {
        MenuItem menuItem = items[position];
        position++;
        return menuItem;
    }

    @Override
    public boolean hasNext() {
        if((position>=items.length) || items[position]==null){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public void remove() {

        if(position<=0){
            throw new IllegalStateException("You cannot remove an item until you have done at least one next()");
        }

        if(items[position-1]!=null){
            for(int i=position-1;i<(items.length-1);i++){
                items[i] = items[i+1];

            }
            items[items.length-1] = null;
        }


    }
}
