package com.yunpan.udpcast

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.yunpan.udpcast.ui.theme.UDPCastTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UDPCastTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BroadcastScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BroadcastScreen() {
    UDPCastTheme {
        Scaffold(topBar = {
            CenterAlignedTopAppBar(title = {
                Row {
                    Text(text = "Broadcast Tester")
                }
            })
        }) {
            Content(paddingValues = it)
        }
    }
}

@Composable
fun Content(paddingValues: PaddingValues) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .verticalScroll(rememberScrollState())
    ) {
        repeat(20) {
            SettingCard()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingCard() {
    var checked by rememberSaveable {
        mutableStateOf(false)
    }
    var text by rememberSaveable { mutableStateOf("{\"context\": \"hello receiver\", \"ip\": \"192.168.198.211\", \"extra\": \"u r a good boy, have a lolipop~\", \"sn\": \"SN123456\"}") }
    Column(
        modifier = Modifier
            .padding(8.dp)
    ) {
        OutlinedCard(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.broadcast),
                contentDescription = "broadcast image",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
            TextField(
                value = text,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { text = it },
                label = { Text(text = "请输入广播内容") }
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "广播已${if (checked) "开启" else "关闭"}", modifier = Modifier.weight(1f))
            Switch(checked = checked, onCheckedChange = { checked = it })
        }
    }
}