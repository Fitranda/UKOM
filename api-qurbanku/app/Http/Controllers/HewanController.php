<?php

namespace App\Http\Controllers;

use App\Models\Hewan;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Facades\URL;

class HewanController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        //
        $data = DB::table('hewans')
        ->join('kategoris','kategoris.idkategori','=','hewans.idkategori')
        ->join('penjuals','penjuals.idpenjual','=','hewans.idpenjual')
        ->select('hewans.*','kategoris.kategori','penjuals.idpenjual','penjuals.penjual','penjuals.alamat','penjuals.ratings')
        ->orderBy('hewans.hewan','asc')
        ->get();

        return response()->json($data);
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create(Request $request)
    {
        //
        $this->validate($request,[
            'idkategori'=>'required | numeric',
            'idhewan'=>'required |unique:hewans',
            'hewan'=>'required |unique:hewans',
            // 'gambar'=>'required',
            'rincian'=>'required',
            'idpenjual'=>'required',
            'flagjual'=>'required',
            'harga'=>'required | numeric'
        ]);

        // $gambar = $request->file('gambar')->getClientOriginalName();
        // $request->file('gambar')->move('upload',$gambar);
        // $request->file('gambar')->storeAs('upload',$gambar);
        // URL::asset('storage/'&& $gambar);

        $data = [
            'idhewan'=>$request->input('idhewan'),
            'idkategori'=>$request->input('idkategori'),
            'hewan'=>$request->input('hewan'),
            'gambar'=>"",
            'harga'=>$request->input('harga'),
            'idpenjual'=>$request->input('idpenjual'),
            'rincian'=>$request->input('rincian'),
            'flagjual'=>$request->input('flagjual')
        ];

        $menu = Hewan::create($data);

        if ($menu) {
            return response()->json([
                'pesan' => 'Data Sudah Disimpan'
            ]);
        }
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        //
    }

    /**
     * Display the specified resource.
     *
     * @param  \App\Models\Hewan  $hewan
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    {
        //
        $data = DB::table('hewans')
        ->join('kategoris','kategoris.idkategori','=','hewans.idkategori')
        ->join('penjuals','penjuals.idpenjual','=','hewans.idpenjual')
        ->select('hewans.*','kategoris.kategori','penjuals.idpenjual','penjuals.penjual','penjuals.alamat','penjuals.ratings')
        ->where('hewans.idpenjual','=',$id)
        ->orderBy('hewans.hewan','asc')
        ->get();

        return response()->json($data);
    }

    /**
     * Display the specified resource.
     *
     * @param  \App\Models\Hewan  $hewan
     * @return \Illuminate\Http\Response
     */

    public function showKategori($id)
    {
        //
        $data = DB::table('hewans')
        ->join('kategoris','kategoris.idkategori','=','hewans.idkategori')
        ->join('penjuals','penjuals.idpenjual','=','hewans.idpenjual')
        ->select('hewans.*','kategoris.kategori','penjuals.idpenjual','penjuals.penjual','penjuals.alamat','penjuals.ratings')
        ->where('hewans.idkategori','=',$id)
        ->orderBy('hewans.hewan','asc')
        ->get();

        return response()->json($data);
    }

    /**
     * Display the specified resource.
     *
     * @param  \App\Models\Hewan  $hewan
     * @return \Illuminate\Http\Response
     */

    public function cari($id)
    {
        //
        $data = DB::table('hewans')
        ->join('kategoris','kategoris.idkategori','=','hewans.idkategori')
        ->join('penjuals','penjuals.idpenjual','=','hewans.idpenjual')
        ->select('hewans.*','kategoris.kategori','penjuals.idpenjual','penjuals.penjual','penjuals.alamat','penjuals.ratings')
        ->where('hewans.hewan','like','%'.$id.'%')
        ->orderBy('hewans.hewan','asc')
        ->get();

        return response()->json($data);
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  \App\Models\Hewan  $hewan
     * @return \Illuminate\Http\Response
     */
    public function edit(Hewan $hewan)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  \App\Models\Hewan  $hewan
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request,$id)
    {
        //
        $this->validate($request,[
            'idkategori'=>'required | numeric',
            'idhewan'=>'required',
            'hewan'=>'required',
            'rincian'=>'required',
            'idpenjual'=>'required',
            'harga'=>'required | numeric'
        ]);

        if ($request->hasFile('gambar')) {
            $gambar = $request->file('gambar')->getClientOriginalName();
        $request->file('gambar')->move('upload',$gambar);
        $data = [
            'idhewan'=>$request->input('idhewan'),
            'idkategori'=>$request->input('idkategori'),
            'idpenjual'=>$request->input('idpenjual'),
            'hewan'=>$request->input('hewan'),
            'flagjual'=>$request->input('flagjual'),
            'rincian'=>$request->input('rincian'),
            'gambar'=>url('upload/'.$gambar),
            'harga'=>$request->input('harga')
        ];
        } else {
            $data = [
                'idhewan'=>$request->input('idhewan'),
                'idkategori'=>$request->input('idkategori'),
                'idpenjual'=>$request->input('idpenjual'),
                'hewan'=>$request->input('hewan'),
                'flagjual'=>$request->input('flagjual'),
                'rincian'=>$request->input('rincian'),
                'harga'=>$request->input('harga')
            ];
        }

        // return response()->json($data);

        $menu = Hewan::where('idhewan',$id)->update($data);
        if ($menu) {
            return response()->json([
                'pesan' => 'Data Sudah Diubah !'
            ]);
        }
    }

    public function updategambar(Request $request, $id)
    {
        //
        $this->validate($request,[
            'gambar'=>'required'
        ]);

        // $email = str_replace(',','.',$id);
        $gambar = $request->file('gambar')->getClientOriginalName();
        $request->file('gambar')->move('upload',$gambar);
        $data = [
            'gambar'=>url('upload/'.$gambar)
        ];

        // return response()->json($data);

        $menu = Hewan::where('idhewan',$id)->update($data);
        if ($menu) {
            return response()->json([
                'gambar'=>url('upload/'.$gambar),
                'pesan' => 'Data Sudah Disimpan !'
            ]);
        }
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  \App\Models\Hewan  $hewan
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        //
        $menu = Hewan::where('idhewan',$id)->delete();

        if ($menu) {
            return response()->json([
                'pesan' => 'data sudah dihapus'
            ]);
        }
    }
}
