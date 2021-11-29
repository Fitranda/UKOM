<?php

/** @var \Laravel\Lumen\Routing\Router $router */

/*
|--------------------------------------------------------------------------
| Application Routes
|--------------------------------------------------------------------------
|
| Here is where you can register all of the routes for an application.
| It is a breeze. Simply tell Lumen the URIs it should respond to
| and give it the Closure to call when that URI is requested.
|
*/

$router->get('/', function () use ($router) {
    return $router->app->version();
});

$router->get('send_email' ,'Mailcontroller@mail');

$router->get('apiqurbanku/kategori', [ 'uses' => 'KategoriController@index']);

$router->post('apiqurbanku/loginadmin', [ 'uses' => 'AdminController@login']);
$router->post('apiqurbanku/loginpelanggan', [ 'uses' => 'PelangganController@login']);

$router->post('apiqurbanku/lupa', [ 'uses' => 'PelangganController@lupa']);

$router->get('apiqurbanku/login/{id}/{idp}', [ 'uses' => 'PelangganController@loginp']);

$router->get('apiqurbanku/logint/{id}/{idp}', [ 'uses' => 'PenjualController@loginp']);

$router->post('apiqurbanku/loginpenjual', [ 'uses' => 'PenjualController@login']);

$router->group(['prefix' => 'apiqurbanku'], function () use ($router) {


    $router->get('kategori/{id}', ['uses' => 'KategoriController@show']);

    $router->delete('kategori/{id}', ['uses' =>'KategoriController@destroy']);

    $router->put('kategori/{id}', ['uses' => 'KategoriController@update']);

    $router->post('kategori', ['uses' => 'KategoriController@create']);


    $router->post('hewan', ['uses' => 'HewanController@create']);

    $router->get('hewan', [ 'uses' => 'HewanController@index']);

    $router->delete('hewan/{id}', ['uses' =>'HewanController@destroy']);

    $router->get('hewan/{id}', ['uses' => 'HewanController@show']);

    $router->get('hewank/{id}', ['uses' => 'HewanController@showKategori']);

    $router->get('hewanc/{id}', ['uses' => 'HewanController@cari']);

    $router->post('hewan/{id}', ['uses' => 'HewanController@update']);

    $router->post('hewang/{id}', ['uses' => 'HewanController@updategambar']);


    $router->post('pelanggan', ['uses' => 'PelangganController@create']);

    $router->get('pelanggan', [ 'uses' => 'PelangganController@index']);

    $router->delete('pelanggan/{id}', ['uses' =>'PelangganController@destroy']);

    $router->get('pelanggan/{id}', ['uses' => 'PelangganController@show']);

    $router->post('pelanggan/{id}', ['uses' => 'PelangganController@update']);

    $router->post('pelanggang/{id}', ['uses' => 'PelangganController@updategambar']);


    $router->post('penjual', ['uses' => 'PenjualController@create']);

    $router->get('penjual', [ 'uses' => 'PenjualController@index']);

    $router->delete('penjual/{id}', ['uses' =>'PenjualController@destroy']);

    $router->get('penjual/{id}', ['uses' => 'PenjualController@show']);

    $router->post('penjual/{id}', ['uses' => 'PenjualController@update']);

    $router->post('penjualg/{id}', ['uses' => 'PenjualController@updategambar']);


    $router->post('admin', ['uses' => 'AdminController@create']);

    $router->get('admin', [ 'uses' => 'AdminController@index']);

    $router->delete('admin/{id}', ['uses' =>'AdminController@destroy']);

    $router->get('admin/{id}', ['uses' => 'AdminController@show']);

    $router->post('admin/{id}', ['uses' => 'AdminController@update']);


    $router->post('order', ['uses' => 'OrderController@create']);

    $router->get('order', [ 'uses' => 'OrderController@index']);

    $router->get('order/{id}', ['uses' => 'OrderController@show']);

    $router->get('orderh/{id}', ['uses' => 'OrderController@showh']);

    $router->get('orderht/{id}', ['uses' => 'OrderController@showht']);

    $router->get('orderp/{id}', ['uses' => 'OrderController@showp']);

    $router->get('orderpt/{id}', ['uses' => 'OrderController@showpt']);

    $router->get('order/{a}/{b}', ['uses' => 'OrderController@showtgl']);

    $router->get('order/{id}/{a}/{b}', ['uses' => 'OrderController@showall']);

    $router->post('order/{id}', ['uses' => 'OrderController@update']);

    $router->post('orderf/{id}', ['uses' => 'OrderController@updateflag']);


    $router->post('detail', ['uses' => 'DetailController@create']);

    $router->get('detail', [ 'uses' => 'DetailController@index']);

    $router->get('detail/{id}', ['uses' => 'DetailController@show']);

    $router->post('detail/{id}', ['uses' => 'DetailController@update']);


    $router->post('komen', ['uses' => 'KomenController@create']);

    $router->get('komen', [ 'uses' => 'KomenController@index']);

    $router->delete('komen/{id}', ['uses' =>'KomenController@destroy']);

    $router->get('komen/{id}', ['uses' => 'KomenController@show']);


    $router->post('favorit', ['uses' => 'FavoritController@create']);

    $router->get('favorit/{id}', ['uses' => 'FavoritController@index']);

    $router->post('favorit/{id}', ['uses' => 'FavoritController@update']);

    $router->get('favorit/{id}/{idp}', ['uses' => 'FavoritController@show']);


    $router->post('notifp', ['uses' => 'NotifPController@create']);

    $router->get('notifp/{id}', ['uses' => 'NotifPController@index']);

    $router->delete('notifp/{id}', ['uses' => 'NotifPController@destroy']);


    $router->post('notift', ['uses' => 'NotifTController@create']);

    $router->get('notift/{id}', ['uses' => 'NotifTController@index']);

    $router->delete('notift/{id}', ['uses' => 'NotifTController@destroy']);

});
