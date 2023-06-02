package com.freecourses.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.freecourses.R;
import com.freecourses.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    ImageSlider imageSlide;
    public View root;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
         root= binding.getRoot();
        ImageSlider imageSlider=root.findViewById(R.id.imageSlider);
        List<SlideModel> slideModels=new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.slide1, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.slide2, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.slide3, ScaleTypes.FIT));
        imageSlider.setImageList(slideModels);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        ImageSlider imageSlider=root.findViewById(R.id.imageSlider);
        List<SlideModel> slideModels=new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.slide1, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.slide2, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.slide3, ScaleTypes.FIT));
        imageSlider.setImageList(slideModels);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}