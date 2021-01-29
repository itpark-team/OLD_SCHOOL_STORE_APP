package com.example.old_school_store_app.views.map;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.old_school_store_app.R;
import com.example.old_school_store_app.models.DataStorage;
import com.example.old_school_store_app.views.main.MainActivity;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.map.MapObject;
import com.yandex.mapkit.map.MapObjectCollection;
import com.yandex.mapkit.map.MapObjectTapListener;
import com.yandex.mapkit.map.PlacemarkMapObject;
import com.yandex.mapkit.mapview.MapView;
import com.yandex.runtime.image.ImageProvider;

public class MapFragment extends Fragment
{
    private final Point CAMERA_TARGET = new Point(53.243325,34.363731);

    private MapView mapView;
    private MapObjectCollection mapObjects;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        mapView = view.findViewById(R.id.mapview);
        mapView.getMap().move(
                new CameraPosition(CAMERA_TARGET, 11.0f, 0.0f, 0.0f));
        mapObjects = mapView.getMap().getMapObjects().addCollection();

        createMapObjects();

        return view;
    }

    private void createMapObjects()
    {
        Context context = (Context) DataStorage.Get("context");

        PlacemarkMapObject point1 = mapObjects.addPlacemark(new Point(53.243880, 34.362213));
        point1.setOpacity(0.5f);
        point1.setIcon(ImageProvider.fromResource(context, R.drawable.mark2));
        point1.setUserData("Точка 1. Любят девочек.\nЧасы работы: пн-вск: 10:00-19:00\nТелефон:+7(999)299-88-99");
        point1.addTapListener(pointMapObjectTapListener);


        PlacemarkMapObject point2 = mapObjects.addPlacemark(new Point(53.275516, 34.316101));
        point2.setOpacity(0.5f);
        point2.setIcon(ImageProvider.fromResource(context, R.drawable.mark2));
        point2.setUserData("Точка 2. Любят девочек.\nЧасы работы: пн-вск: 10:00-19:00\nТелефон:+7(999)299-88-99");
        point2.addTapListener(pointMapObjectTapListener);

        PlacemarkMapObject point3 = mapObjects.addPlacemark(new Point(53.308033, 34.301776));
        point3.setOpacity(0.5f);
        point3.setIcon(ImageProvider.fromResource(context, R.drawable.mark2));
        point3.setUserData("Точка 3. Любят девочек.\nЧасы работы: пн-вск: 10:00-19:00\nТелефон:+7(999)299-88-99");
        point3.addTapListener(pointMapObjectTapListener);
    }

    @Override
    public void onStop() {
        mapView.onStop();
        MapKitFactory.getInstance().onStop();
        super.onStop();
    }

    @Override
    public void onStart() {
        super.onStart();
        MapKitFactory.getInstance().onStart();
        mapView.onStart();
    }

    private MapObjectTapListener pointMapObjectTapListener = new MapObjectTapListener() {
        @Override
        public boolean onMapObjectTap(@NonNull MapObject mapObject, @NonNull Point point)
        {
            String message = (String) mapObject.getUserData();

            MainActivity mainActivity = (MainActivity) DataStorage.Get("mainActivity");

            AlertDialog.Builder dialogWindow  = new AlertDialog.Builder(mainActivity);
            dialogWindow.setMessage(message);
            dialogWindow.setTitle("Информация о магазине");
            dialogWindow.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            dialogWindow.setCancelable(true);
            dialogWindow.create().show();

            return true;
        }
    };

}
