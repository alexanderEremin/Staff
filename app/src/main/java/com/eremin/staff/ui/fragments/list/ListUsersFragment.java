package com.eremin.staff.ui.fragments.list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.eremin.staff.R;
import com.eremin.staff.databinding.FragmentListUsersBinding;
import com.eremin.staff.interfaces.IClickRecycler;
import com.eremin.staff.other.ConstantManager;
import com.eremin.staff.ui.adapters.AdapterListUsers;

public class ListUsersFragment extends Fragment implements IClickRecycler {
    private FragmentListUsersBinding binding;
    private ListUsersViewModel mViewModel;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentListUsersBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.listUsers.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.fab.setOnClickListener(view1 -> NavHostFragment
                .findNavController(ListUsersFragment.this)
                .navigate(R.id.action_ListUsersFragment_to_RefactorUserFragment));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ListUsersViewModel.class);
        mViewModel.getStatusLoadingData().observe(getViewLifecycleOwner(), this::statusLoad);
        mViewModel.getData().observe(getViewLifecycleOwner(), userData -> {
            if (userData.size() > 0){
                binding.message.setVisibility(View.GONE);
                binding.listUsers.setAdapter(new AdapterListUsers(userData, this));
            }
        });
    }

    @Override
    public void clickRecycler(String id) {
        Bundle bundle = new Bundle();
        bundle.putString(ConstantManager.CLICK_RECYCLER, id);
        NavHostFragment.findNavController(this).navigate(R.id.action_ListUsersFragment_to_RefactorUserFragment, bundle);
    }

    /**
     * Процесс загрузки даных из БД
     * @param aBoolean true идет загрузка false ничего не грузим
     */
    private void statusLoad(Boolean aBoolean) {
        if(aBoolean)
            binding.progress.setVisibility(View.VISIBLE);
        else
            binding.progress.setVisibility(View.GONE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}