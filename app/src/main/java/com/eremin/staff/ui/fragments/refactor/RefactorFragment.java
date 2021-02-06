package com.eremin.staff.ui.fragments.refactor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.eremin.staff.R;
import com.eremin.staff.databinding.FragmentRefactorBinding;
import com.eremin.staff.other.ConstantManager;
import com.eremin.staff.mapdata.UserData;

import java.util.Objects;

public class RefactorFragment extends Fragment {
    private RefactorViewModel mViewModel;
    private FragmentRefactorBinding binding;
    private String idUser;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRefactorBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.cancel.setOnClickListener(cancel -> NavHostFragment
                .findNavController(RefactorFragment.this)
                .navigate(R.id.action_RefactorUserFragment_to_ListUsersFragment));
        binding.safe.setOnClickListener(safe -> {
            UserData data = new UserData();
            data.setId(idUser);
            data.setLastName(Objects.requireNonNull(binding.dataLastName.getText()).toString());
            data.setFirstName(Objects.requireNonNull(binding.dataFirstName.getText()).toString());
            data.setFatherName(Objects.requireNonNull(binding.dataFatherName.getText()).toString());
            data.setPosition(Objects.requireNonNull(binding.dataPosition.getText()).toString());
            mViewModel.safeData(data);
            clearField();
        });
        binding.delete.setOnClickListener(delete -> {
            mViewModel.deleteData(idUser);
            NavHostFragment.findNavController(this).navigate(R.id.action_RefactorUserFragment_to_ListUsersFragment);
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(RefactorViewModel.class);
        if (getArguments() != null) {
            idUser = getArguments().getString(ConstantManager.CLICK_RECYCLER);
            mViewModel.loadDataUser(idUser);
        }else{
            binding.delete.setVisibility(View.GONE);
        }
        mViewModel.getStatusLoadingData().observe(getViewLifecycleOwner(), this::statusLoad);
        mViewModel.getErrors().observe(getViewLifecycleOwner(), this::viewErrors);
        mViewModel.getUserData().observe(getViewLifecycleOwner(), userData -> {
            binding.dataFirstName.setText(userData.getFirstName());
            binding.dataLastName.setText(userData.getLastName());
            binding.dataFatherName.setText(userData.getFatherName());
            binding.dataPosition.setText(userData.getPosition());
        });
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

    /**
     * Отобразить ошибку
     * @param error текст ошибки
     */
    private void viewErrors(String error){
        Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show();
    }

    /**
     * Очистка текстовых полей
     */
    private void clearField(){
        binding.dataFirstName.setText("");
        binding.dataLastName.setText("");
        binding.dataFatherName.setText("");
        binding.dataPosition.setText("");
        idUser = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}