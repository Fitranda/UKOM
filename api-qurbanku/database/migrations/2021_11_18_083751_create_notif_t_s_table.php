<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateNotifTSTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('notif_t_s', function (Blueprint $table) {
            $table->increments('idnotif');
            $table->integer('idpelanggan');
            $table->integer('idpenjual');
            $table->string('notif');
            $table->date('tanggal');
            $table->time('waktu');
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('notif_t_s');
    }
}
