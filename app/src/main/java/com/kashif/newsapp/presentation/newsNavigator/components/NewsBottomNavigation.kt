package com.kashif.newsapp.presentation.newsNavigator.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import com.kashif.newsapp.R
import com.kashif.newsapp.presentation.Dimens.Elevation
import com.kashif.newsapp.presentation.Dimens.ExtraSmallPadding2
import com.kashif.newsapp.presentation.Dimens.IconSize

/**
 * Created by Mohammad Kashif Ansari on 22,May,2024
 */

@Composable
fun NewsBottomNavigation(items:List<BottomNavigationItems>,
                         selected:Int,
                         onItemClick:(Int)->Unit){

    NavigationBar (modifier = Modifier.fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.background,
        tonalElevation = Elevation){
        items.forEachIndexed{index,item->
        NavigationBarItem(selected = index==selected, onClick = { onItemClick(index) }, icon = {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(painter = painterResource(id = item.icon), contentDescription =null,
                    modifier = Modifier.size(IconSize))
                Spacer(modifier = Modifier.height(ExtraSmallPadding2))
                Text(text = item.text, style = MaterialTheme.typography.labelSmall)

            }
        }, colors = NavigationBarItemDefaults.colors(
            selectedIconColor = MaterialTheme.colorScheme.primary,
            selectedTextColor = MaterialTheme.colorScheme.primary,
            unselectedIconColor = colorResource(id = R.color.body),
            unselectedTextColor = colorResource(id = R.color.body),
            indicatorColor = MaterialTheme.colorScheme.background
        ))}

    }
}
data class BottomNavigationItems(@DrawableRes val icon:Int,
    val text:String)