package com.example.licenta;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AdapterExp extends BaseAdapter implements Filterable {
    private Context context;
 //   private String[]nume;
  //  private int[] img;
    ArrayList<Expense> expense;
    private LayoutInflater inflater;
    ArrayList<Expense> filterExpense;
    CustomFilter filter;

    public AdapterExp(Context c,ArrayList<Expense> expense){
        context=c;
        this.expense=expense;
        this.filterExpense=expense;
    }

    public void remove(Expense e){
        expense.remove(e);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {

        return expense.size();
    }

    @Override
    public Object getItem(int position) {

        return expense.get(position);
    }

    @Override
    public long getItemId(int position) {

        return expense.indexOf(getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(inflater==null){
            inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView==null){
            convertView=inflater.inflate(R.layout.grid_exp,null);
        }
        ImageView imageView=(ImageView)convertView.findViewById(R.id.img_grid_exp);
        TextView textView=(TextView)convertView.findViewById(R.id.text_grid_exp);
        imageView.setImageResource(expense.get(position).getImg());
        textView.setText(expense.get(position).getNume());
       /* convertView.setId(position);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(context,TransactionDetail.class);
                intent.putExtra("id","sasa");
                intent.putExtra("type","dsadsa");
                intent.putExtra("description","dsdsd");
                intent.putExtra("price","fdsfsd");
                intent.putExtra("data","dfsdfsd");
                context.startActivity(intent);
            }
        });*/
        return convertView;
    }

    @Override
    public Filter getFilter() {
        if(filter==null){
            filter=new CustomFilter();
        }
        return filter;
    }




    class CustomFilter extends Filter{

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results=new FilterResults();
            if(constraint!=null && constraint.length() > 0){
                constraint=constraint.toString().toUpperCase();
                ArrayList<Expense> filter=new ArrayList<Expense>();
                //filter
                for(int i=0;i<filterExpense.size();i++){
                    if(filterExpense.get(i).getNume().toUpperCase().contains(constraint)){
                        Expense e=new Expense(filterExpense.get(i).getNume(),filterExpense.get(i).getImg());
                        filter.add(e);}
                }
                results.count=filter.size();
                results.values=filter;
            }
            else{
                results.count=filterExpense.size();
                results.values=filterExpense;
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            expense=(ArrayList<Expense>) results.values;
            notifyDataSetChanged();
        }
    }
}
