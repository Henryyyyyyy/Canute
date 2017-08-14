package me.henry.canute;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import me.henry.canutecore.activity.ProxyActivity;
import me.henry.canutecore.app.Canute;
import me.henry.canutecore.delegates.CanuteDelegate;

public class MainActivity extends ProxyActivity {
    @Override
    public CanuteDelegate setRootDelegate() {
        return new ExampleDelegate();
    }
    //  Toast.makeText(Canute.getAppContext(),"haha",Toast.LENGTH_SHORT).show();


}
