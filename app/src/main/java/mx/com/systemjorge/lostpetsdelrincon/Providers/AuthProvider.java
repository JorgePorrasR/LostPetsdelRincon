package mx.com.systemjorge.lostpetsdelrincon.Providers;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class AuthProvider {

    FirebaseAuth mAuth;

    public AuthProvider() {
        mAuth = FirebaseAuth.getInstance();
    }

    public Task<AuthResult> register(String email, String password) {
        return mAuth.createUserWithEmailAndPassword(email, password);
    }

    public Task<AuthResult> login(String email, String password) {
        return mAuth.signInWithEmailAndPassword(email, password);
    }

    public void logout() {
        mAuth.signOut();
    }

    //Método para obtener el ID del usuario
    public String getId() {
        return mAuth.getCurrentUser().getUid();
    }

    public boolean existSession() {
        boolean exist = false;
        if (mAuth.getCurrentUser() != null) { //Si la sesión existe...
            exist = true;
        }
        return exist;
    }

}

