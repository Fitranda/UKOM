<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateHewansTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('hewans', function (Blueprint $table) {
            $table->string('idhewan')->primary();
            $table->integer('idkategori');
            $table->integer('idpenjual');
            $table->string('hewan');
            $table->double('harga');
            $table->string('rincian');
            $table->string('gambar');
            $table->integer('flagjual');
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
        Schema::dropIfExists('hewans');
    }
}
