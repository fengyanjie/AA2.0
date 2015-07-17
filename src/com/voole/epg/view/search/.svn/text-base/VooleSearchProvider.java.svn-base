package com.voole.epg.view.search;

import java.util.List;

import android.app.SearchManager;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.util.Log;

import com.voole.epg.Config;
import com.voole.epg.corelib.model.auth.AuthManager;
import com.voole.epg.corelib.model.movies.Detail;
import com.voole.epg.corelib.model.movies.Film;
import com.voole.epg.corelib.model.movies.FilmAndPageInfo;
import com.voole.epg.corelib.model.movies.MovieManager;
import com.voole.epg.corelib.model.movies.SearchManager.SearchType;

public class VooleSearchProvider extends ContentProvider {
	private static final String[] COLUMNS = { 
		"_id",
		SearchManager.SUGGEST_COLUMN_TEXT_1,
		SearchManager.SUGGEST_COLUMN_TEXT_2,
		SearchManager.SUGGEST_COLUMN_INTENT_DATA,
		"packName",
	};
	
	public static String AUTHORITY = "com.voole.epg.view.search.VooleSearchProvider";
	// UriMatcher stuff
    private static final int SEARCH_SUGGEST = 2;
    private static final UriMatcher sURIMatcher = buildUriMatcher();
	
	private static UriMatcher buildUriMatcher() {
        UriMatcher matcher =  new UriMatcher(UriMatcher.NO_MATCH);
        // to get suggestions...
        matcher.addURI(AUTHORITY, SearchManager.SUGGEST_URI_PATH_QUERY, SEARCH_SUGGEST);
        matcher.addURI(AUTHORITY, SearchManager.SUGGEST_URI_PATH_QUERY + "/*", SEARCH_SUGGEST);
        return matcher;
    }
	
	@Override
	public int delete(Uri arg0, String arg1, String[] arg2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getType(Uri uri) {
		switch (sURIMatcher.match(uri)) {
        case SEARCH_SUGGEST:
            return SearchManager.SUGGEST_MIME_TYPE;
        default:
            throw new IllegalArgumentException("Unknown URL " + uri);
    }
	}

	@Override
	public Uri insert(Uri arg0, ContentValues arg1) {
		return null;
	}

	@Override
	public boolean onCreate() {
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		Log.d(Config.TAG, "query-->uri-->" + uri);
		Log.d(Config.TAG, "query-->projection-->" + projection);
		Log.d(Config.TAG, "query-->selection-->" + selection);
		Log.d(Config.TAG, "query-->selectionArgs-->" + selectionArgs);
		Log.d(Config.TAG, "query-->sortOrder-->" + sortOrder);
		switch (sURIMatcher.match(uri)) {
		case SEARCH_SUGGEST:
			if (selectionArgs == null && projection == null) {
				throw new IllegalArgumentException(
						"selectionArgs must be provided for the Uri: " + uri);
			}
			Log.d(Config.TAG, "query-->uri---last->" + uri.getLastPathSegment());
			if (selectionArgs != null) {
				for (String string : selectionArgs) {
					Log.d(Config.TAG, "query-->selectionArgs-->" + string);
				}
				return getSuggestions(selectionArgs[0]);
			}
			return getSuggestions(uri.getLastPathSegment());
		default:
			throw new IllegalArgumentException("Unknown Uri: " + uri);
		}
	}
	
	private Cursor getSuggestions(String query) {
		Log.d(Config.TAG, "query-->getSuggestions-->" + query);
		if(query == null || query.equals("")){
			return null;
		}
		MatrixCursor cursor = new MatrixCursor(COLUMNS);
		/*for(int i = 0; i < 5; i++){
			cursor.addRow(new String[]{"" + i , "voole name" + i, "voole des" + i, "voole search" + i, "com.voole.epg"});
		}*/
//		List<Film> films = MovieManager.GetInstance().searchMoviesPinYin(query, 1, 10);
//		for(int i = 0; i < films.size(); i++){
//			cursor.addRow(new String[]{films.get(i).getFilmID() , films.get(i).getFilmName(), 
//						films.get(i).getDescription(), films.get(i).getSourceUrl(), "com.voole.epg"});
//		}
		FilmAndPageInfo filmAndPageInfo = com.voole.epg.corelib.model.movies.SearchManager.GetInstance().searchIndex(query, 1, 10,SearchType.All,"");
//		FilmAndPageInfo filmAndPageInfo = com.voole.epg.corelib.model.movies.SearchManager.GetInstance().searchMovie(query, 1, 7,SearchType.Keyword,"");
		if (filmAndPageInfo != null) {
			List<Film> films = filmAndPageInfo.getFilmList();
			if (films != null && films.size() > 0) {
				for(int i = 0; i < films.size(); i++){
//					String srcUrl = AuthManager.GetInstance().getUrlList().getPayList() + "&ctype=4&filmmid=" + films.get(i).getMid();
//					String srcUrl = detail.getFilm().getSourceUrl();
					String srcUrl = AuthManager.GetInstance().getUrlList().getPayList() + "&ctype=4&filmmid=" + films.get(i).getMid();
					cursor.addRow(new String[]{films.get(i).getMid() , films.get(i).getFilmName(), 
								films.get(i).getDescription(), srcUrl, "com.voole.epg"});
				}
			}else {
				String[] strings = new String[5];
				strings[0] = "1";
				strings[1] = "未搜索到关键字为"+ query + "的影片";
				strings[2] = "";
				strings[3] = "";
				strings[4] = "com.voole.epg";
				cursor.addRow(strings);
			}
		}else {
			cursor.addRow(new String[]{"1" , "", 
					"未搜索到结果,请检测网络或重试", "", "com.voole.epg"});
		}
		return cursor;
	}
	
	@Override
	public int update(Uri arg0, ContentValues arg1, String arg2, String[] arg3) {
		// TODO Auto-generated method stub
		return 0;
	}

}
