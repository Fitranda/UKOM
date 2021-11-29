<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateOrdersTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('orders', function (Blueprint $table) {
            $table->string('notransaksi')->primary();
            $table->string('alamattujuan');
            $table->integer('idpenjual');
            $table->integer('idpelanggan');
            $table->double('total');
            $table->double('bayar')->default(0);
            $table->double('kembali')->default(0);
            $table->date('tanggal');
            $table->time('waktu');
            $table->integer('flagkirim');
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
        Schema::dropIfExists('orders');
    }
}
