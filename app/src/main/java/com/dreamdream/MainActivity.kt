package com.dreamdream

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import com.dreamdream.ui.theme.DreamDreamTheme
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dreamdream.ui.screens.HomeScreen
import com.dreamdream.ui.screens.RuidoSessentaScreen
import com.dreamdream.ui.screens.RuidoTrintaScreen
import com.dreamdream.ui.screens.SplashScreen
import com.dreamdream.ui.screens.TimerRuidoScreen
import com.dreamdream.ui.screens.WelcomeScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DreamDreamTheme {
              // navegação principal
                AppNavigation()
                }
            }
        }
    }

@Composable
fun AppNavigation(){
    //Controlador de navegação
    val navController: NavHostController = rememberNavController()

    //Configuração do NavHost com as rotas e telas composable
    NavHost(
        navController = navController,
        startDestination = "splash_screen" //tela inicial
    ) {
        //rota SplashScreen
        composable(route = "splash_screen"){
            SplashScreen(navController = navController)
        }
        composable(route = "welcome_screen"){
            WelcomeScreen(navController = navController)
        }
        composable(route = "home_screen"){
            HomeScreen(navController = navController)
        }
        composable(route = "timer_ruido_screen"){
            TimerRuidoScreen(navController = navController)
        }
        composable(route = "ruido_trinta_screen"){
           RuidoTrintaScreen(navController = navController)
        }
        composable(route = "ruido_sessenta_screen"){
            RuidoSessentaScreen(navController = navController)
        }
    }
}


