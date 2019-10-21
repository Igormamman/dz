package com.example.dz;


import android.content.Context;
import android.graphics.Color;
import android.icu.text.Transliterator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class first_frag extends Fragment {
    private ChangeFragment Changer;
    private static final String ITEMCOUNT ="ItemCount";
    private dzAdapter Adapt;
    private int COUNT;
    static first_frag newInstance(int param) {
        first_frag fragment = new first_frag();
        Bundle bundle = new Bundle();
        bundle.putInt(ITEMCOUNT, param);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Changer = (ChangeFragment) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if(savedInstanceState != null) {
            COUNT = savedInstanceState.getInt(ITEMCOUNT);
        }
        else if(args != null)
            COUNT = args.getInt(ITEMCOUNT);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.first_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle args = getArguments();
        if(Adapt == null)
        { Adapt = new dzAdapter();
            if(savedInstanceState != null)
            { Adapt.setData(savedInstanceState.getInt(ITEMCOUNT));}
            else if(args != null)
            {   Adapt.setData(args.getInt(ITEMCOUNT));}
        }


        RecyclerView recyclerView = view.findViewById(R.id.arr);

        int columns=getResources().getBoolean(R.bool.isHorizontal)?4:3;
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),columns));
        recyclerView.setAdapter(Adapt);
        Button button = view.findViewById(R.id.clickable);
        View.OnClickListener v = v1 -> {Adapt.number1.add(Adapt.number1.size()+1);COUNT++;Adapt.notifyItemInserted(Adapt.number1.size()+1);};
        button.setOnClickListener(v);

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle out) {
        super.onSaveInstanceState(out);
        out.putInt(ITEMCOUNT, COUNT);
    }


    class dzViewHolder extends RecyclerView.ViewHolder
    {

        private final TextView numeral;
        private dzViewHolder(@NonNull View itemView)
        {
            super(itemView);
            numeral = itemView.findViewById(R.id.iNumber);
            numeral.setOnClickListener(view  -> Changer.Change(getAdapterPosition()+1));
        }
    }

    class dzAdapter extends RecyclerView.Adapter<dzViewHolder>
    {
        final ArrayList<Integer> number1;
        private dzAdapter(){ number1=new ArrayList<>(); }

        @NonNull
        @Override
        public dzViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view= LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.item_view, parent,false);
            return new dzViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull dzViewHolder holder, int position) {
            holder.numeral.setTextColor(position%2==0 ? Color.RED : Color.BLUE);
            holder.numeral.setText(String.valueOf(number1.get(position)));
        }


        void setData(int size) {
            for (int i = 1; i <= size; i++) {
                number1.add(i);
                notifyItemInserted(i);
            }
        }

        @Override
        public int getItemCount() {
            return number1.size();
        }

    }

    public interface ChangeFragment {
        void Change (int position);
    }
}