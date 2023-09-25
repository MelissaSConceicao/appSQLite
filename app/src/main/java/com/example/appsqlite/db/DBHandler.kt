package com.example.appsqlite.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.appsqlite.model.CourseModel

class DBHandler //creando um construtor para o banco de dados
    (context: Context?) :
    SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    // método para criar um registro no banco
    override fun onCreate(db: SQLiteDatabase) {
        //Criando uma tabela, nomeando as colunas e definindo os tipos de cada coluna
        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT,"
                + DURATION_COL + " TEXT,"
                + DESCRIPTION_COL + " TEXT,"
                + TRACKS_COL + " TEXT)")
        //Exceção para o caso da ação de registro falhar
        db.execSQL(query)
    }

    // método para adicionar um novo curso
    fun addNewCourse(
        name: String?,
        duration: String?,
        description: String?,
        tracks: String?
    ) {
        // Criando uma variável para o banco e chamando um writable method para poder escrever dentro do banco
        val db = this.writableDatabase
        // criando uma variável que vai guardar todos valores do registro
        val values = ContentValues()
        // Colocando o valor de cada coluna dentro da variável
        values.put(NAME_COL, name)
        values.put(DURATION_COL, duration)
        values.put(DESCRIPTION_COL, description)
        values.put(TRACKS_COL, tracks)
        // Inserindo o registro na tabela
        db.insert(TABLE_NAME, null, values)
        // terminando a ação e fechando o banco
        db.close()
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Checando se a tabela já existe
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    companion object {
        /*
        * Criando as seguintes constantes para o banco de dados:
        * Nome do banco
        * Versão do banco
        * Nome da tabela
        * Atributos:
        *  - ID
        *  - Nome
        *  - Duração
        *  - Descrição
        *  - Aulas do curso
        * */
        private const val DB_NAME = "coursedb"
        private const val DB_VERSION = 1
        private const val TABLE_NAME = "mycourses"
        private const val ID_COL = "id"
        private const val NAME_COL = "name"
        private const val DURATION_COL = "duration"
        private const val DESCRIPTION_COL = "description"
        private const val TRACKS_COL = "tracks"
    }

}