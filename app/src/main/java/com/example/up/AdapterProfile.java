package com.example.up;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.List;

public class AdapterProfile extends BaseAdapter {

    private Context mContext;
    List<MaskaProfile> maskaProfiles;

    public AdapterProfile(Context mContext, List<MaskaProfile> maskaProfiles) {
        this.mContext = mContext;
        this.maskaProfiles = maskaProfiles;
    }

    @Override
    public int getCount() {
        return maskaProfiles.size();
    }

    @Override
    public Object getItem(int i) {
        return maskaProfiles.get(i);
    }

    @Override
    public long getItemId(int i) {
        return maskaProfiles.get(i).getID();
    }

    public static Bitmap loadContactPhoto(ContentResolver cr, long id, Context context) {
        Uri uri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, id);
        InputStream input = ContactsContract.Contacts.openContactPhotoInputStream(cr, uri);
        if (input == null) {
            Resources res = context.getResources();
            return BitmapFactory.decodeResource(res, R.drawable.logo);
        }
        return BitmapFactory.decodeStream(input);
    }

    private Bitmap getUserImage(String encodedImg) {

        if (encodedImg != null && !encodedImg.equals("null")) {
            byte[] bytes = Base64.decode(encodedImg, Base64.DEFAULT);
            return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        } else
            return BitmapFactory.decodeResource(mContext.getResources(), R.drawable.logo);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = View.inflate(mContext, R.layout.maska_profile, null);
        View v1 = View.inflate(mContext, R.layout.maska_plus, null);

        ImageView Image = v.findViewById(R.id.ppz);
        TextView Time = v.findViewById(R.id.time);

        MaskaProfile mask = maskaProfiles.get(position);
        Time.setText(mask.getTime());


        Image.setImageBitmap(getUserImage(mask.getImage()));


        //Переход на детальную информацию
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intenDetalis = new Intent(mContext, DetailsImage.class);
                intenDetalis.putExtra("Pictures", mask);
                mContext.startActivity(intenDetalis);

            }
        });
        v1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intenDetalis = new Intent(mContext, DetailsImage.class);
                intenDetalis.putExtra("Pictures", mask);
                mContext.startActivity(intenDetalis);

            }
        });


        return v;
    }
}