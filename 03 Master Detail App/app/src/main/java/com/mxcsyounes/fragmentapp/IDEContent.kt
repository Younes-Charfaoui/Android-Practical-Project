package com.mxcsyounes.fragmentapp

import java.util.*

object IDEContent {

    val ideList: MutableList<IDEItem> = ArrayList()
    val ideMap: MutableMap<String, IDEItem> = HashMap()


    init {
        addItem(IDEItem("1" , "Data Grip" , "DataGrip is a cross-platform DBA tool which is aimed at developers who work with SQL databases. It has built-in drivers that support the following vendors: Amazon Redshift, Apache Cassandra, Apache Derby, Apache Hive, Azure SQL Database, ClickHouse, Exasol, Greenplum, H2, HSQLDB, IBM Db2, MariaDB, Microsoft SQL Server, MySQL, Oracle, PostgreSQL, Snowflake, SQLite, Sybase ASE, and Vertica."))
        addItem(IDEItem("2" , "Android Studio" , "Android Studio is the official integrated development environment for Google's Android operating system, built on JetBrains' IntelliJ IDEA software and designed specifically for Android development. It is available for download on Windows, macOS and Linux based operating systems."))
        addItem(IDEItem("3" , "PHP Storm" , "PhpStorm is a commercial, cross-platform IDE for PHP, built by the Czech Republic-based company JetBrains. PhpStorm provides an editor for PHP, HTML and JavaScript with on-the-fly code analysis, error prevention and automated refactorings for PHP and JavaScript code."))
    }

    private fun addItem(item: IDEItem) {
        ideList.add(item)
        ideMap[item.id] = item
    }


    data class IDEItem(val id: String, val name: String, val description: String) {
        override fun toString(): String = name
    }
}
