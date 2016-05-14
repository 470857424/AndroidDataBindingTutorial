package com.github.captain_miao.databinding.tutorial.recycleview;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.captain_miao.databinding.tutorial.BR;
import com.github.captain_miao.databinding.tutorial.R;
import com.github.captain_miao.databinding.tutorial.databinding.ObservableRecyclerItemViewBinding;
import com.github.captain_miao.databinding.tutorial.listener.OnViewClickListener;
import com.github.captain_miao.databinding.tutorial.model.ObservableVehicleInfo;
import com.github.captain_miao.recyclerviewutils.BaseWrapperRecyclerAdapter;

import java.util.List;


/**
 * @author YanLu
 * @since 16/4/27
 */
public class ObservableVehicleListAdapter extends BaseWrapperRecyclerAdapter<ObservableVehicleInfo, RecyclerView.ViewHolder> {

    public ObservableVehicleListAdapter() {
    }

    public ObservableVehicleListAdapter(List<ObservableVehicleInfo> items) {
        addAll(items);
    }

    @Override
    public RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.observable_recycler_item_view, parent, false);

        return new ObservableVehicleListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ObservableVehicleInfo info = getItem(position);
        if(holder instanceof ViewHolder){
            ViewDataBinding binding = ((ViewHolder) holder).getBinding();
            binding.setVariable(BR.info, info);
            binding.setVariable(BR.itemCLick, itemListener);
            binding.setVariable(BR.selectedCLick, selectedListener);
            binding.executePendingBindings();
        }
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding mBinding;

        public ViewHolder(View itemView) {
            super(itemView);
            mBinding = DataBindingUtil.bind(itemView);
        }

        public ViewDataBinding getBinding() {
            return mBinding;
        }
    }

    OnViewClickListener itemListener = new OnViewClickListener() {

        @Override
        public void onClick(View v) {
            ObservableRecyclerItemViewBinding binding = DataBindingUtil.findBinding(v);
            ObservableVehicleInfo data = binding.getInfo();
            Toast.makeText(v.getContext(), data.getBrand(), Toast.LENGTH_SHORT).show();

        }
    };

    OnViewClickListener selectedListener = new OnViewClickListener() {

        @Override
        public void onClick(View v) {
            ObservableRecyclerItemViewBinding binding = DataBindingUtil.findBinding(v);
            ObservableVehicleInfo data = binding.getInfo();
               for(ObservableVehicleInfo e : getList()){
                   if(e.getIsSelected()) {
                       e.setIsSelected(false);
                       break;
                   }
               }
            data.setIsSelected(true);
        }
    };
}
