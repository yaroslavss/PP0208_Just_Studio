package com.yara.juststudioapp.di

import android.content.Context
import com.yara.juststudioapp.di.modules.RemoteModule
import com.yara.juststudioapp.ui.confirm.ConfirmViewModel
import com.yara.juststudioapp.ui.login.LoginViewModel
import com.yara.juststudioapp.ui.profile.ProfileViewModel
import com.yara.juststudioapp.ui.register.RegisterViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    // all modules
    modules = [
        RemoteModule::class
    ]
)
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(loginViewModel: LoginViewModel)

    fun inject(profileViewModel: ProfileViewModel)

    fun inject(registerViewModel: RegisterViewModel)

    fun inject(confirmViewModel: ConfirmViewModel)
}