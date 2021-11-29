<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreatePenjualsTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('penjuals', function (Blueprint $table) {
            $table->increments('idpenjual');
            $table->string('penjual');
            $table->string('telp');
            $table->string('gambar')->nullable();
            $table->string('email');
            $table->string('password');
            $table->string('alamat');
            $table->string('link');
            $table->integer('status');
            $table->double('ratings');
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
        Schema::dropIfExists('penjuals');
    }
}
